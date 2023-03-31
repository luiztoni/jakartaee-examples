package br.edu.worker;

import java.time.Instant;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Startup
@Singleton
public class Worker {
 
	@PostConstruct
	public void init() {
		System.out.println("init()");
	}
	
	@Lock(LockType.READ)
    @Schedule(second = "10", minute = "*", hour = "*", persistent = false)
	private void logTime() {
		System.out.println("for each 10s: logTime() "+Instant.now());
	} 
}
