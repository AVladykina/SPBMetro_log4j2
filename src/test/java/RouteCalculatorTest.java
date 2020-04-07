import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

public class RouteCalculatorTest extends TestCase {

    List<Station> route, route1, route2;

    RouteCalculator calculator;

    StationIndex stationIndex;

    List<Station> connections1;
    List<Station> connections2;

    Station start, start1, start2;

    Station end, end1, end2;

    @Override
    protected void setUp() throws Exception {


        Line line1 = new Line(1, "Красная");
        Line line2 = new Line(2, "Синяя");
        Line line3 = new Line(2, "Зеленая");

        Station station1 = new Station("Девяткино",line1);
        Station station2 = new Station("Парнас",line2);
        Station station3 = new Station("Беговая",line3);
        Station station1_2 = new Station("Гражданский проспект",line1);
        Station station1_3 = new Station("Академическая",line1);
        Station station2_2 = new Station("Проспект просвещения",line2);
        Station station3_2 = new Station("Новокрестовская",line3);

        route = new ArrayList<>();
        route.add(new Station("Девяткино",line1));
        route.add(new Station("Гражданский проспект",line1));
        route.add(new Station("Академическая",line1));


        line1.addStation(station1);
        line1.addStation(station1_2);
        line1.addStation(station1_3);
        line2.addStation(station2);
        line2.addStation(station2_2);
        line3.addStation(station3);
        line3.addStation(station3_2);

        connections1 = new ArrayList<>();
        connections1.add(station1);
        connections1.add(station2);
        connections2 = new ArrayList<>();
        connections2.add(station3);
        connections2.add(station2);

        stationIndex = new StationIndex();
        stationIndex.number2line = new HashMap<>();
        stationIndex.stations = new TreeSet<>();
        stationIndex.connections = new TreeMap<>();

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(station1);
        stationIndex.addStation(station2_2);
        stationIndex.addStation(station1_3);
        stationIndex.addStation(station2);
        stationIndex.addStation(station1_2);
        stationIndex.addStation(station3);
        stationIndex.addStation(station3_2);

        stationIndex.addConnection(connections1);
        stationIndex.addConnection(connections2);

        calculator = new RouteCalculator(stationIndex);

        route2 = new ArrayList<>();
        route2.add(station1_3);
        route2.add(station1_2);
        route2.add(station1);
        route2.add(station2);
        route2.add(station3);
        route2.add(station3_2);


        start2 = station1_3;
        end2 = station3_2;

        route1 = new ArrayList<>();
        route1.add(station1_3);
        route1.add(station1_2);
        route1.add(station1);
        route1.add(station2);
        route1.add(station2_2);

        start1 = station1_3;
        end1 = station2_2;

        start = station1;
        end = station1_3;


    }
    @Test
    public void testCalculateDurationOnTheLine() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 5;
        assertEquals(expected,actual);
    }

    @Test
    public void testCalculateDurationWithOneConnections() {
        double actual = RouteCalculator.calculateDuration(route1);
        double expected = 11;
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateDurationWithTwoConnections() {
        double actual = RouteCalculator.calculateDuration(route2);
        double expected = 13.5;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetShortestRouteWithTwoConnections() {
        List<Station> actual = calculator.getShortestRoute(start2,end2);
        List<Station> expected = route2;
        assertEquals(expected,actual);
    }

    @Test
    public void testGetShortestRouteWithOneConnections() {
        List<Station> actual = calculator.getShortestRoute(start1,end1);
        List<Station> expected = route1;
        assertEquals(expected,actual);
    }

    @Test
    public void testGetShortestRouteOnTheLine() {
        List<Station> actual = calculator.getShortestRoute(start,end);
        List<Station> expected = route;
        assertEquals(expected,actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
