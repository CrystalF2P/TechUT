package pl.mkrause.tests;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.mkrause.domain.Fish;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class FishTest {

    @Autowired
    FishShopManager fishShopManager;

    @Autowired
    private SessionFactory hsf;

    @Test
    public void addPlaneCheck() {
        Fish fish = new Fish("A330","XX-100", 250, new Date(115, 05, 15));

        fishShopManager.addFish(fish);

        long id = plane.getId();

        Plane retrievedPlane = (Plane) hsf.getCurrentSession().get(Plane.class, id);

        assertEquals(plane.getId(), retrievedPlane.getId());
    }

    @Test
    public void getPlaneByIdCheck() {
        Plane plane = new Plane("A330","XX-101", 250, new Date(115, 05, 15));

        airlinesManager.addPlane(plane);

        Plane retrievedPlane = airlinesManager.findPlaneById(plane.getId());

        assertEquals(plane.getId(), retrievedPlane.getId());
    }

    @Test
    public void getPlaneBySideNumber() {
        Plane plane = new Plane("A330","XX-102", 250, new Date(115, 05, 15));

        airlinesManager.addPlane(plane);

        Plane retrievedPlane = airlinesManager.findPlaneBySideNumber(plane.getSideNumber());

        assertEquals(plane.getId(), retrievedPlane.getId());
    }

    @Test
    public void updatePlaneCheck() {
        Plane plane = new Plane("A330","XX-103", 250, new Date(115, 05, 15));

        airlinesManager.addPlane(plane);

        String model = "A331";

        plane.setModel(model);

        airlinesManager.updatePlane(plane);

        assertEquals(plane.getModel(), model);
    }

    @Test
    public void deletePlaneCheck() {
        Plane plane = new Plane("A330","XX-104", 250, new Date(115, 05, 15));

        airlinesManager.addPlane(plane);

        List<Plane> allPlanesBefore = hsf.getCurrentSession().getNamedQuery("plane.all").list();
        int before = allPlanesBefore.size();

        airlinesManager.deletePlane(plane.getId());

        List<Plane> allPlanesAfter = hsf.getCurrentSession().getNamedQuery("plane.all").list();
        int after = allPlanesAfter.size();

        assertEquals(after, before - 1);
    }

    @Test
    public void getAllPlanesCheck() {
        List<Plane> allPlanesBefore = airlinesManager.getAllPlanes();
        int before = allPlanesBefore.size();

        Plane plane = new Plane("A330","XX-105", 250, new Date(115, 05, 15));
        airlinesManager.addPlane(plane);

        List<Plane> allPlanesAfter = airlinesManager.getAllPlanes();
        int after = allPlanesAfter.size();

        assertEquals(after, before + 1);
    }

    @Test
    public void deleteAllPlanesCheck() {
        Plane plane = new Plane("A330","XX-106", 250, new Date(115, 05, 15));
        airlinesManager.addPlane(plane);

        List<Plane> allPlanesBefore = airlinesManager.getAllPlanes();
        int before = allPlanesBefore.size();

        airlinesManager.deleteAllPlanes();

        List<Plane> allPlanesAfter = airlinesManager.getAllPlanes();
        int after = allPlanesAfter.size();

        assertEquals(after + before, before);
    }
}
