//package snake.tests;
//
//import org.junit.jupiter.api.Test;
//import snake.architecture.Map;
//import snake.architecture.Point;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class MapTest {
//
//    Map map = new Map(10,10);
//    snake.architecture.Point point1 = new Point(2,2);
//    snake.architecture.Point point2 = new Point(15,2);
//    snake.architecture.Point point3 = new Point(7,9);
//    snake.architecture.Point point4 = new Point(0,0);
//    snake.architecture.Point point5 = new Point(-4,-2);
//
//    /*Point appleCord = new Point(5, 5);
//    Apple apple = new Apple(appleCord);*/
//    //map.setCreatureOnMap(apple);
//    //создать несколько объектов. проверить setcreature на пустой и не пустой клетке + удалить + проверка непустой клетки)
//
//    @Test
//    void pointInMap(){
//        assertEquals(false, map.pointWithinMapBorders(point1));
//    }
//    @Test
//    void pointOutMap(){
//        assertEquals(true, map.pointWithinMapBorders(point2));
//    }
//    @Test
//    void pointOnBorder(){
//        assertEquals(false, map.pointWithinMapBorders(point3));
//    }
//    @Test
//    void pointInAngle(){
//        assertEquals(false, map.pointWithinMapBorders(point4));
//    }
//    @Test
//    void pointOtr(){
//        assertEquals(true, map.pointWithinMapBorders(point5));
//    }
//    @Test
//    void checkIsEmpty(){
//        assertEquals(true, map.IsEmpty(point1));
//    }
//}
