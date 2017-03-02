package mtp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;

@SpringBootApplication//(exclude = {CassandraDataAutoConfiguration.class})
public class SampleOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleOrderApplication.class, args);
	}

}
