package org.example.Domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteDaoImplement {

    public void saveCliente(Cliente nuevoCliente) {

        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            if (!entityManager.contains(nuevoCliente)) {
                entityManager.persist(nuevoCliente);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


    public void estadisticas() {
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {

            TypedQuery<Cliente> todo = entityManager.createQuery("SELECT c from Cliente c", Cliente.class);
            todo.getResultList().forEach(System.out::println);

            TypedQuery<Long> totalVentasQuery = entityManager.createQuery("SELECT SUM(c.totalventas) FROM Cliente c", Long.class);
            Long totalVentas = totalVentasQuery.getSingleResult();


            TypedQuery<Double> promedioVentasQuery = entityManager.createQuery("SELECT AVG(c.totalventas) FROM Cliente c WHERE c.estado = 'Activo'", Double.class);
            Double promedioVentasClientesActivos = promedioVentasQuery.getSingleResult();

            TypedQuery<Long> cantidadClientesInactivosQuery = entityManager.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.estado = 'Inactivo' AND c.totalventas > 0", Long.class);
            Long cantidadClientesInactivosConVentasMayoresA0 = cantidadClientesInactivosQuery.getSingleResult();

            System.out.println("Estadísticas:");
            System.out.println("Total de Ventas entre Todos los Clientes: " + totalVentas);
            System.out.println("Promedio de Ventas de Clientes Activos: " + promedioVentasClientesActivos);
            System.out.println("Cantidad de Clientes Inactivos con Ventas Mayores a 0: " + cantidadClientesInactivosConVentasMayoresA0);
        } finally {
            entityManager.close();
        }
    }




        public Cliente getCliente(Integer id) {
            Cliente cliente = null;
            EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
            try {
                TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.id = :id", Cliente.class);
                query.setParameter("id", id);
                List<Cliente> resultado = query.getResultList();
                if (!resultado.isEmpty()) {
                    cliente = resultado.get(0);

                    System.out.println("Información del Cliente:");
                    System.out.println("ID: " + cliente.getId());
                    System.out.println("Nombre: " + cliente.getNombre());
                    System.out.println("Total Ventas: " + cliente.getTotalventas());
                    System.out.println("Estado: " + cliente.getEstado());
                }
            } finally {
                entityManager.close();
            }
            return cliente;
        }

    public List<Cliente> getClientesActivosConVentasSuperiores(Long totalVentasMinimo) {
        List<Cliente> clientes = null;
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.estado = 'Activo' AND c.totalventas > :totalVentasMinimo", Cliente.class);
            query.setParameter("totalVentasMinimo", totalVentasMinimo);
            clientes = query.getResultList();
        } finally {
            entityManager.close();
        }
        return clientes;
    }
}





