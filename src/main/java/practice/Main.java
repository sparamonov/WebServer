package practice;

/**
 * Created by paramonovss on 29.03.17.
 */
public class Main {
    public static void main(String[] args) {
        Elephant elephant = new Elephant();
        boolean isDangerous = elephant.isDangerous();
        if (elephant.canJump())
            jump(elephant);

        Jumpable mouse = new Elephant();
        if (mouse.canJump())
            jump(mouse);

    }


    public static void jump(Jumpable object) {
        System.out.print("Object can jump");
    }
}
