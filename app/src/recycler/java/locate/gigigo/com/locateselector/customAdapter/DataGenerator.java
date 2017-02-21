package locate.gigigo.com.locateselector.customAdapter;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import locate.gigigo.com.locateselector.customAdapter.entities.ImageCell;
import locate.gigigo.com.locateselector.customAdapter.entities.TextCell;

public class DataGenerator {

  private static String[] categories =
      new String[] { "animals", "architecture", "nature", "people", "tech" };

  public static List<Object> generateRandomDataList(int num) {
    List<Object> data = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      if (i % 2 == 0) {
        data.add(generateRandomImageData());
      } else {
        data.add(generateRandomTextData(i));
      }
    }
    return data;
  }

  public static ImageCell generateRandomImageData() {
    return new ImageCell("http://placeimg.com/250/250/" + getCategory());
  }

  public static TextCell generateRandomTextData(int index) {
    return new TextCell("item" + index);
  }

  private static String getCategory() {
    int random = new Random().nextInt();
    int index = Math.abs(random) % (categories.length - 1);
    return categories[index];
  }
}
