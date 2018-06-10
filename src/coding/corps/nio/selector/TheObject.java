package coding.corps.nio.selector;

/**
 * Created by Administrator on 2018/6/10.
 */
public class TheObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TheObject{" +
                "name='" + name + '\'' +
                '}';
    }
}
