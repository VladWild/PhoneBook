package com.vladislavgusev.bookphone.parser.command;

import com.vladislavgusev.bookphone.requests.CommandType;

public interface Parser {
    CommandType getCommandType();
    String getName();
    String getNumber();
}
