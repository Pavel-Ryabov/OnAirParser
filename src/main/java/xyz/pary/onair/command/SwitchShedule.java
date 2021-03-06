package xyz.pary.onair.command;

import xyz.pary.onair.command.parameter.Duration;

/**
 *
 * Команда перехода на другое расписание
 */
public class SwitchShedule extends Command {

    public SwitchShedule() {
        super(CommandKey.SWITCH_SHEDULE);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Duration getDuration() {
        return new Duration();
    }

    @Override
    public String toSheduleRow() {
        return commandKey.getKey();
    }
}
