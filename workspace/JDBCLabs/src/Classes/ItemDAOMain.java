package Classes;

import com.javatunes.util.ItemDAO;
import com.javatunes.util.MusicItem;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

public class ItemDAOMain {

  public static void main(String[] args) {
//        try {
//            MusicItem musicItem = new ItemDAO().searchById(3L);
//            System.out.println(musicItem.toString());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            for(MusicItem mi: new ItemDAO().searchByKeyword("of")){
//                System.out.println("Name: "+ mi.getTitle());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    try {
      new ItemDAO().create(
          new MusicItem(101L, "New Title", "Artist", new java.util.Date(), new BigDecimal(10),
              new BigDecimal(1))
      );
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
