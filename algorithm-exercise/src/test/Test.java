package test;
import java.util.Arrays;
import java.util.Arrays;

/**
 * @Description TODO
 * @Author 86156
 * @Date 2023/11/3 19:00
 * @Version 1.0
 **/
public class Test {
    private int a; //只有该类创建了对象时，类的成员变量才会被分配空间，同时会有一个默认值
    private String s;
    private final static int b;

    // 如果打算在构造器或者初始化块中对fianl成员变量进行初始化，则不要在初始化之前就访问该成员变量的值。
    static {
        b = 1;
    }

    //与成员变量不同，系统不会自动为局部变量分配空间与初始化，因此final局部变量可以在后面使用的时候再赋值，但是也只能赋值一次。

    public void test1(final int a) {
        //下方语句会报错
//            a = 10;
    }

    //final修饰基本类型变量时，不能重新赋值；final修饰引用类型变量时，也不能重新赋值。但是，引用类型变量存储的是地址，
    // 是指向对象的地址，地址不能改变，并不意味着对象本身不能改变
}

    class NBAplayer {
        private String team;

        //无参构造器
        public NBAplayer() {

        }

        //有参构造器
        public NBAplayer(String team) {
            this.team = team;
        }

        //team的get方法
        public String getTeam() {
            return this.team;
        }

        //team的set方法
        public void setTeam(String team) {
            this.team = team;
        }
    }
class Demo01 {
    public static void main(String[] args) {
        //final修饰数组变量，数组是引用变量
        final int[] Score = {20, 30, 40, 50};
        //对数组进行排序
        Arrays.sort(Score);
        //数组值可以更改
        Score[1] = 4;
        //指向的对象不可更改，因此下方语句非法
        //Score = null;
        //final修饰NABplayer类变量，是引用变量
        NBAplayer N = new NBAplayer("Lakers");
        //改变该引用变量指向对象的实例变量
        N.setTeam("Nuggets");
        //对N重新赋值，非法
        //N = new NBAplayer("Heat");
    }
}



