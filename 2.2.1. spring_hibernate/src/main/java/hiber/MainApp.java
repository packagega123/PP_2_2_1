package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();

      CarService carService = context.getBean(CarService.class);

      carService.add(new Car("A", 1));
      carService.add(new Car("B", 2));
      carService.add(new Car("C", 3));
      carService.add(new Car("D", 4));

      List<Car> cars = carService.listCars();

      users.get(0).setCar(cars.get(0));
      users.get(1).setCar(cars.get(1));
      users.get(2).setCar(cars.get(2));
      users.get(3).setCar(cars.get(3));

      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println("Пользователь с \"A 1\"");
      System.out.println(userService.findUserByMS("A", 1));

      context.close();
   }
}