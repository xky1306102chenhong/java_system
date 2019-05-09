/**
 * @author Chris Chen
 * @date 2019/4/26 下午7:11
 */

interface AddInterface{
    public abstract int add(int a, int b);
}

public class CommandTest {
    public static void main(String[] args) {
        AddInterface addInterface = (int a, int b) -> {
            return a + b;
        };
        addInterface.add(1,2);
    }
}
