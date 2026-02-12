package main;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}



//lab5/
//        │
//        ├── data/
//        │   └── data.csv
//│
//        ├── src/
//        │   └── main/
//        │       └── java/
//        │           └── ru/
//        │               └── lab5/
//        │                   ├── main.Main.java
//│                   │
//        │                   ├── main.objects/
//        │                   │   ├── HumanBeing.java
//│                   │   ├── Coordinates.java
//│                   │   ├── Car.java
//│                   │   └── WeaponType.java
//│                   │
//        │                   └── utils/
//        │                       ├── CollectionManager.java
//│                       ├── FileManager.java
//│                       ├── InputManager.java
//│                       ├── HumanBeingReader.java
//│                       ├── Command.java
//│                       ├── CommandManager.java
//│                       │
//        │                       ├── commands/
//        │                       │   ├── HelpCommand.java
//│                       │   ├── InfoCommand.java
//│                       │   ├── ShowCommand.java
//│                       │   ├── AddCommand.java
//│                       │   ├── UpdateIdCommand.java
//│                       │   ├── RemoveByIdCommand.java
//│                       │   ├── ClearCommand.java
//│                       │   ├── SaveCommand.java
//│                       │   ├── ExecuteScriptCommand.java
//│                       │   ├── ExitCommand.java
//│                       │   ├── AddIfMaxCommand.java
//│                       │   ├── AddIfMinCommand.java
//│                       │   ├── RemoveGreaterCommand.java
//│                       │   ├── FilterContainsNameCommand.java
//│                       │   ├── FilterLessThanMinutesOfWaitingCommand.java
//│                       │   └── FilterGreaterThanCarCommand.java
//│                       │
//        │                       └── exceptions/
//        │                           ├── InvalidDataException.java
//│                           ├── CommandExecutionException.java
//│                           └── ScriptRecursionException.java
//│
//        └── README.md