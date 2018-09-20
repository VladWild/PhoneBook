package com.vladislavgusev.bookphone.parsers;

import com.vladislavgusev.bookphone.requests.CommandType;

public interface Parser {
    CommandType getCommandType();
    String getName();
    String getNumber();
}
