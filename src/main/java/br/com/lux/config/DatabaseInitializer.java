package br.com.lux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception
    {
        // Reserva Trigger
        // jdbcTemplate.execute("CREATE TRIGGER reserva_modificada AFTER UPDATE ON Reserva FOR EACH ROW CALL \"br.com.lux.domain.trigger.ReservationTrigger\"");
        // jdbcTemplate.execute("CREATE TRIGGER reserva_criada AFTER INSERT ON Reserva FOR EACH ROW CALL \"br.com.lux.domain.trigger.ReservationTrigger\"");

        // User Trigger
    }
}
