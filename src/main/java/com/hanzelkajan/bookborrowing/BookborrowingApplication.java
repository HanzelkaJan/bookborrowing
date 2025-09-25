package com.hanzelkajan.bookborrowing;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.sql.rowset.JdbcRowSet;

@SpringBootApplication
@Log
public class BookborrowingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookborrowingApplication.class, args);
	}

}
