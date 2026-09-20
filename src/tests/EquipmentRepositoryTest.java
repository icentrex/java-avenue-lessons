package tests;

import mentor1.model.Equipment;
import mentor1.model.EquipmentType;
import mentor1.model.User;
import mentor1.repository.EquipmentRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EquipmentRepositoryTest {

    private EquipmentRepository repository;

    @Before
    public void setUp() {
        repository = new EquipmentRepository();
    }

    // --- вспомогательные фабрики: reduce™ дублирование при создании объектов ---

    private EquipmentType type(String name) {
        return new EquipmentType(name);
    }

    private Equipment equipment(EquipmentType type, String brand, int serial) {
        return new Equipment(type, brand, serial);
    }

    private void addSampleEquipment() {
        repository.add(equipment(type("Монитор"), "Samsung", 100));
        repository.add(equipment(type("Мышка"), "Logitech", 200));
    }

    // --- кейсы ---

    @Test
    public void add_assignsIdAndIncrements() {
        Equipment added = repository.add(equipment(type("Монитор"), "Samsung", 100));

        assertTrue("id должен быть присвоен репозиторием", added.getId() > 0);
        assertEquals(1, repository.getEquipmentsList().size());
    }

    @Test
    public void findById_returnsPresent_forExisting_andEmpty_forMissing() {
        Equipment added = repository.add(equipment(type("Монитор"), "Samsung", 100));

        assertTrue(repository.findEquipmentById(added.getId()).isPresent());
        assertTrue(repository.findEquipmentById(999).isEmpty());
    }

    @Test
    public void deleteById_returnsTrue_forExisting_false_forMissing() {
        Equipment added = repository.add(equipment(type("Монитор"), "Samsung", 100));

        assertTrue(repository.deleteEquipmentById(added.getId()));
        assertTrue(repository.findEquipmentById(added.getId()).isEmpty());
        assertFalse(repository.deleteEquipmentById(added.getId())); // уже удалена
    }

    @Test
    public void getFreeEquipments_returnsOnlyFree_doesNotThrowWithNullUser() {
        addSampleEquipment();
        // сейчас обе техники свободны (user == null)

        List<Equipment> free = repository.getFreeEquipments();

        assertEquals("обе свободны -> размер 2", 2, free.size());
        // главное: метод НЕ упал с NullPointerException, хотя user у всей техники == null
    }

    @Test
    public void getUserEquipments_filtersByUser_doesNotThrowWithFreeEquipment() {
        User user = new User("Иван", "999-45-66");
        Equipment assigned = repository.add(equipment(type("Монитор"), "Samsung", 100));
        Equipment free = repository.add(equipment(type("Мышка"), "Logitech", 200));

        repository.assignEquipment(user, assigned.getId());

        List<Equipment> result = repository.getUserEquipments(user.getId());

        assertEquals(1, result.size());
        assertEquals(assigned.getId(), result.getFirst().getId());
        // free-техника (user == null) не должна уронить метод и не попала в список
    }

    @Test
    public void assign_mutatesEquipmentUser_detach_returnsEquipmentToFree() {
        User user = new User("Мария", "888-45-66");
        Equipment equipment = repository.add(equipment(type("Наушники"), "Sony", 300));

        assertTrue(repository.assignEquipment(user, equipment.getId()));
        assertEquals("после assign техника закреплена за юзером", user, equipment.getUser());
        assertTrue("закреплённая не должна быть свободной", repository.getFreeEquipments().isEmpty());

        assertTrue(repository.detachEquipment(equipment.getId()));
        assertNull("после detach user должен стать null", equipment.getUser());
        assertEquals(1, repository.getFreeEquipments().size());
    }
}