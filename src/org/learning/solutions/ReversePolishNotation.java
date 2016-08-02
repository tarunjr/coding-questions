package org.learning.solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int computed = 0;
        for(String token: tokens) {
        	if(SimpleOperatorFactory.isOperator(token)) {
        		Operator op = SimpleOperatorFactory.getOperator(token);
        		if(op.isUnary()) {
        			computed = op.computeUnary(stack.removeFirst());
        		} else {
        			int op2 = stack.removeFirst();
            		int op1 = stack.removeFirst(); 
        			computed = op.computeBinary(op1, op2);
        		}
        		stack.addFirst(computed);
        	} else {
        		Integer n = Integer.parseInt(token);
        		stack.addFirst(n);
        	}
        }
        return stack.removeFirst().intValue();
    }
    public interface Operator {
        int computeBinary(int operand1, int operand2);
        int computeUnary(int operand1);
        boolean isUnary();
    }
   
    public static class OperatorAddition implements Operator {
        public int computeBinary(int op1, int op2) {
            return op1 + op2;
        }
        public int computeUnary(int operand1) {
            throw new UnsupportedOperationException();
        }
        public boolean isUnary() {
            return false;
        }
    }
    public static class OperatorSubtract implements Operator {
        public int computeBinary(int op1, int op2) {
            return op1 - op2;
        }
        public int computeUnary(int operand1) {
            throw new UnsupportedOperationException();
        }
        public boolean isUnary() {
            return false;
        }
    }
    public static class OperatorMultiplication implements Operator {
        public int computeBinary(int op1, int op2) {
            return op1 * op2;
        }
        public int computeUnary(int operand1) {
            throw new UnsupportedOperationException();
        }
        public boolean isUnary() {
            return false;
        }
    }
    public static class OperatorDivision implements Operator {
        public int computeBinary(int op1, int op2) {
            return op1 / op2;
        }
        public int computeUnary(int operand1) {
            throw new UnsupportedOperationException();
        }
        public boolean isUnary() {
            return false;
        }
    }
    
    public static class SimpleOperatorFactory {
        private static Operator addOperator = null;
        private static Operator subOperator = null;
        private static Operator multOperator = null;
        private static Operator divOperator = null;
        
        public static Operator getOperator(String token) {
            if(token == "+") {
                if(addOperator == null)
                    addOperator = new OperatorAddition();
                return addOperator;
            }
            else if (token == "-") {
                if(subOperator == null)
                    subOperator = new OperatorSubtract();
                return subOperator;
            }
            else if (token == "*") {
                if(multOperator == null)
                    multOperator = new OperatorMultiplication();
                return multOperator;
            }
            else if (token == "/") {
                if(divOperator == null)
                    divOperator = new OperatorDivision();
                return divOperator;
            }
            return null;
        }
        public static boolean isOperator(String token) {
        	if(token == "+" || token == "-" || token == "*" || token == "/")
        		return true;
        	else
        		return false;
        }
    }
}