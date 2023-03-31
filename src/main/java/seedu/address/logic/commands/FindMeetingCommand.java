package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.*;


import java.time.LocalDateTime;

import java.util.function.Predicate;


public class FindMeetingCommand extends Command{
    public static final String COMMAND_WORD = "meetingFind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings with "
            + "the specified date (case-insensitive)  and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "09-11-2001 11:30";

    private final LocalDateTime meetingStart;

    public FindMeetingCommand(LocalDateTime meetingStart) {
        requireNonNull(meetingStart);
        this.meetingStart = meetingStart;
    }

    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        model.updateFilteredMeetingList((Predicate<MeetingWithPerson>) new MeetingStartDatePredicate(meetingStart));
        return new CommandResult(
                String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, model.getFilteredMeetingList().size()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMeetingCommand // instanceof handles nulls
                && meetingStart.equals(((FindMeetingCommand) other).meetingStart)); // state check
    }
}


