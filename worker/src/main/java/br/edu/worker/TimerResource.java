package br.edu.worker;

import java.time.Instant;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;
import jakarta.ejb.TimerService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("timers")
@Stateless
public class TimerResource {

	@Resource
	private TimerService timerService;

	@GET
	public String startTimer() {
		timerService.createTimer(1000, 1000, "simple trigger.");
		return "Timer scheduled";
	}

	@Timeout
	public void run() {
		System.out.println("run() " + Instant.now());
	}

}