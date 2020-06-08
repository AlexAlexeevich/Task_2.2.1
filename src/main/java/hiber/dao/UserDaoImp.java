package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByHisCar(String name, int series) {
//      Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE car.name =: name and car.series =: series");
//      query.setParameter("name", name);
//      query.setParameter("series", series);
//      User user = (User) query.getSingleResult();
//      return user;
      Session session = sessionFactory.openSession();
      String hql = "FROM User WHERE car.name =: name and car.series =: series";
      Query query = session.createQuery(hql);
      query.setParameter("name", name);
      query.setParameter("series", series);
      User user = (User) query.uniqueResult();
      session.close();
      return user;
   }

}
