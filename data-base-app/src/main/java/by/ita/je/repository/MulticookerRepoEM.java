package by.ita.je.repository;

import by.ita.je.models.Multicooker;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
public class MulticookerRepoEM {

    @PersistenceContext
    private final EntityManager entityManager;

    public MulticookerRepoEM(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public Multicooker findMulticookerByNumber(Integer number){
            return entityManager.find(Multicooker.class, number);
    }

    @Transactional
    public Multicooker insertMulticooker(Multicooker multicooker){
        entityManager.createNativeQuery("INSERT INTO MULTICOOKER (number, type, description," +
                        " isTouchScreen, numberModes, price, energy, registered)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Multicooker.class)
                .setParameter(1, multicooker.getNumber())
                .setParameter(2, multicooker.getType())
                .setParameter(3, multicooker.getDescription())
                .setParameter(4, multicooker.getIsTouchScreen())
                .setParameter(5, multicooker.getNumberModes())
                .setParameter(6, multicooker.getPrice())
                .setParameter(7, multicooker.getEnergy())
                .setParameter(8, multicooker.getRegistered())
                .executeUpdate();

        return findMulticookerByNumber(multicooker.getNumber());

    }

    public Multicooker updateMulticooker(Multicooker multicooker){
        entityManager.merge(multicooker);
        return findMulticookerByNumber(multicooker.getNumber());
    }

    public Multicooker deleteMulticooker(Integer number) {
        Multicooker multicookerDelete = findMulticookerByNumber(number);
        entityManager.remove(multicookerDelete);
        return multicookerDelete;
    }
    public List<Multicooker> readAll(){
        try {
            return entityManager.createNativeQuery("SELECT * FROM MULTICOOKER", Multicooker.class).getResultList();
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    public void deleteALL() {
        entityManager.createNativeQuery("DELETE FROM MULTICOOKER", Multicooker.class);
    }
}
