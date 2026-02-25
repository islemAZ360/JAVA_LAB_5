package main.utils;

public interface Command {

    String grtName();

    String getDescription();

    void execute(String[] arg);
}