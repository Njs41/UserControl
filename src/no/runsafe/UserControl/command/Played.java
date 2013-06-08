package no.runsafe.UserControl.command;

import no.runsafe.UserControl.database.PlayerSessionLog;
import no.runsafe.framework.command.player.PlayerAsyncCommand;
import no.runsafe.framework.server.player.RunsafePlayer;
import no.runsafe.framework.timer.IScheduler;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormat;

import java.util.HashMap;

public class Played extends PlayerAsyncCommand
{
	public Played(IScheduler scheduler, PlayerSessionLog database)
	{
		super("played", "Tells you how much time you have spent on the server", "runsafe.usercontrol.played", scheduler);
		this.database = database;
	}

	@Override
	public String OnAsyncExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		Duration played = database.GetTimePlayed(executor);
		return String.format("You have played &4%s", formatTime(played));
	}

	private String formatTime(Duration time)
	{
		if (time == null)
			return "null";

		Period period = new Period(time, DateTime.now(), output_format);
		return PeriodFormat.getDefault().print(period);
	}

	private final PlayerSessionLog database;
	private final PeriodType output_format = PeriodType.standard().withMillisRemoved().withSecondsRemoved();
}