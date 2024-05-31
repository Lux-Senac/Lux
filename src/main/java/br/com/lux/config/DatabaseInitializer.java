package br.com.lux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception
    {
        // Verifica se a tabela de controle existe
        String checkTableSql = "SELECT COUNT(1) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'INITIALIZATION_CONTROL'";
        Integer tableExists = jdbcTemplate.queryForObject(checkTableSql, Integer.class);

        // Se a tabela de controle não existe, cria a tabela e executa o script de inicialização
        if (tableExists == 0)
        {
            // Cria a tabela de controle
            String createTableSql = "CREATE TABLE INITIALIZATION_CONTROL (SCRIPT_NAME VARCHAR(255), EXECUTED BOOLEAN)";
            jdbcTemplate.execute(createTableSql);

            // Carrega o script SQL do classpath
            Resource resource = new ClassPathResource("/db/data.sql");
            Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            String sqlScript = FileCopyUtils.copyToString(reader);

            // Divide o script em instruções individuais
            String[] sqlStatements = sqlScript.split(";");

            // Executa cada instrução SQL
            for (String statement : sqlStatements)
            {
                jdbcTemplate.execute(statement);
            }

            // Insere um registro na tabela de controle para indicar que o script de inicialização foi executado
            String insertControlRecordSql = "INSERT INTO INITIALIZATION_CONTROL (SCRIPT_NAME, EXECUTED) VALUES ('data.sql', true)";
            jdbcTemplate.execute(insertControlRecordSql);
        }
    }
}
