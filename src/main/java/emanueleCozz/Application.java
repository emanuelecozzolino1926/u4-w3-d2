package emanueleCozz;

import emanueleCozz.dao.EventoDAO;
import emanueleCozz.entities.Evento;
import emanueleCozz.entities.TipoEvento;
import emanueleCozz.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EventoDAO eventoDAO = new EventoDAO(em);

        Evento e1 = new Evento(
                "Evento di prova",
                LocalDate.now().plusDays(3),
                "Prima prova JPA/Hibernate",
                TipoEvento.PUBBLICO,
                50
        );

        eventoDAO.save(e1);
        System.out.println("SALVATO -> " + e1);

        Evento trovato = eventoDAO.getById(e1.getId());
        System.out.println("TROVATO -> " + trovato);

        eventoDAO.delete(e1.getId());
        System.out.println("ELIMINATO id=" + e1.getId());

        em.close();
        JPAUtil.getEntityManagerFactory().close();
    }
}
