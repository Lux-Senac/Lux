package br.com.lux.domain.reservation.trigger;

import br.com.lux.services.email.Email;
import br.com.lux.services.email.EmailService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextListener;
import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.SQLException;


public class ReservationTrigger implements Trigger
{
    @Override
    public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type)
    {
       // System.out.println("ReservationTrigger inicializada");
    }

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException
    {
        if (oldRow != null && newRow != null)
        {
            if (!oldRow[4].equals(newRow[4]))
            {
                System.out.println("Status da reserva modificada: " + newRow[4]);
            }
        }
        else if (newRow != null)
        {
            System.out.println("Nova reserva criada com status: " + newRow[4]);
        }

        Email email = new Email("destinatario@email.com", "Assunto do email", "Corpo do email");

    }

    @Override
    public void close()
    {
       // System.out.println("Trigger fechada");
    }

    @Override
    public void remove()
    {
       // System.out.println("Trigger removida");
    }
}
