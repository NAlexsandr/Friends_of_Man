package Controler;

import Data.*;
import Service.Counter;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controler {
    ArrayList<Pets> pets = new ArrayList<>();             /*Создаем Таблицу Pets (Домашние животные)*/
    ArrayList<PackAnimals> packAnimals = new ArrayList<>();  /*Создаем Таблицу PackAnimals (Вьючные животные)*/
    public ArrayList<Pets> GetPets() {
        return pets;
    }
    public ArrayList<PackAnimals> GetpackAnimals() {
        return packAnimals;
    }
    public static Counter count = new Counter();                  /*Счетчик записей животных в таблицы)*/
    public void AddFriendOfMan() throws IOException {              /*Добавление записи о животном в Pets (Домашние животные)или  PackAnimals (Вьючные животные)*/
        String className;
        String name;
        String вirthday;
        ArrayList<String> instructions = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        System.out.print("Введите название животного (Dogs, Cats, Hamsters, Horses, Camels, Donkeys): ");
        className = in.next();
        while (!className.equals("Dogs") && !className.equals("Cats") && !className.equals("Hamsters")
                && !className.equals("Horses") && !className.equals("Camels") && !className.equals("Donkeys")) {
            System.out.print("Введите название животного (Dogs, Cats, Hamsters, Horses, Camels, Donkeys): ");
            className = in.next();
        }

        System.out.print("Введите Имя животного: ");
        name = in.next();

        System.out.print("Введите день рождения животного в формате ГГ/ММ/DD: ");
        вirthday = in.next();

        System.out.println("Введите команды, которые выполняет животное, чтобы закончить ввод команд напишите " + "\u001B[34m" + "end" + "\u001B[0m");
        String temp;
        temp = in.next();
        while (!(temp.equals("end"))) {
            System.out.println("Вводите команды дальше, чтобы закончить ввод команд напишите " + "\u001B[34m" + "end" + "\u001B[0m");
            instructions.add(temp);
            temp = in.next();
        }
                                              /*Увеличиваем на 1 количество добавленных животных*/
        try {
            if (!(name.isEmpty()) & !(вirthday.isEmpty()) & !(instructions.isEmpty())) { /*Проверяем, что все поля заполнены*/
                count.add();
                FileWriter writer = new FileWriter(count.getFile().getName());
                String strNumber = String.valueOf(count.getCount());
                writer.write(strNumber);
                writer.close();
                                                                 /*Определяем класс и записываем в правильную таблицу*/
                if (className.equals("Dogs")) {
                    pets.add(new Dogs(name, вirthday, instructions));
                }
                if (className.equals("Cats")) {
                    pets.add(new Cats(name, вirthday, instructions));
                }
                if (className.equals("Hamsters")) {
                    pets.add(new Hamsters(name, вirthday, instructions));
                }                                                /*Определяем класс и записываем в правильную таблицу*/
                if (className.equals("Horses")) {
                    packAnimals.add(new Horses(name, вirthday, instructions));
                }
                if (className.equals("Camels")) {
                    packAnimals.add(new Camels(name, вirthday, instructions));
                }
                if (className.equals("Donkeys")) {
                    packAnimals.add(new Donkeys(name, вirthday, instructions));
                }
            }
        } catch (IOException e) {                        /*Возможные исключения при работе с объектом Counter при записи в файл*/
            System.out.println("Ошибка при работе с Объектом Counter");
        }
    }
                                                             /*Выводим на экран таблицу Pets (Домашние животные)*/
    public void PrintTablePets(ArrayList<Pets> animals) {
        if (animals.isEmpty()) { System.out.print("В таблице Pets (Домашние животные) нет записей " + '\n');
        } else {
            int lineNumber = 1;
            for (Pets element : animals) {
                System.out.print(lineNumber + "    ");
                System.out.print(element.toString() + '\n');
                lineNumber = lineNumber + 1;
            }
        }
    }
                                                           /*Выводим на экран таблицу PackAnimals (Вьючные животные)*/
    public void PrintTablePackAnimals(ArrayList<PackAnimals> animals) {
        if (animals.isEmpty()) { System.out.print("В таблице PackAnimals (Вьючные животные) нет записей " + '\n');
        } else {
            int lineNumber = 1;
            for (PackAnimals element : animals) {
                System.out.print(lineNumber + "    ");
                System.out.print(element.toString() + '\n');
                lineNumber = lineNumber + 1;
            }
        }
    }
                             /*Навигация, не очень удачная, но с навигацией всегда так, можно бесконечно ее улучшать*/
    public void Navigator() throws IOException {
        ArrayList<String> instructions = new ArrayList<>();
        String temp;
        int option = -1;
        int optionV = -1;
        int numberAnimals = -1;
        Scanner in = new Scanner(System.in);
        while (option != 4) {
            System.out.print("1. Добавить новое животное" + '\n');
            System.out.print("2. Вывести на экран таблицу Pets (Домашние животные)" + '\n');
            System.out.print("3. Вывести на экран таблицу PackAnimals (Вьючные животные)" + '\n');
            System.out.print("4. Выйти из приложения" + '\n');

            option = in.nextInt();
            if (option == 1) {
                AddFriendOfMan();
            }
            if (option == 2) {
                PrintTablePets(pets);

                System.out.print("   1. Изменить команду конкретному животному: " + '\n');
                System.out.print("   2. Выйти из приложения" + '\n');
                optionV = in.nextInt();
                if (optionV == 1) {
                    System.out.print("Введите НОМЕР животного, которому хотите изменить команды: " + '\n');
                    numberAnimals = in.nextInt();
                     if ((numberAnimals > 0) && (numberAnimals <= pets.size())) {
                        System.out.println("Введите команды, которые выполняет животное, чтобы закончить ввод команд напишите " + "\u001B[34m" + "end" + "\u001B[0m");
                        temp = in.next();
                        while (!(temp.equals("end"))) {
                            System.out.println("Вводите команды дальше, чтобы закончить ввод команд напишите " + "\u001B[34m" + "end" + "\u001B[0m");
                            instructions.add(temp);
                            temp = in.next();
                        }
                        GetPets().get(numberAnimals - 1).setInstructions(instructions);
                    } else {
                        System.out.print("Такого животного в таблице НЕТ: " + '\n');
                    }
                }

                if (optionV == 2) {
                    return;
                }
            }
            if (option == 3) {
                PrintTablePackAnimals(packAnimals);
                System.out.print("   1.  Изменить команду конкретному животному: " + '\n');
                System.out.print("   2.  Выйти из приложения" + '\n');
                option = in.nextInt();
                if (optionV == 1) {
                    System.out.print("Введите НОМЕР животного, которому хотите изменить команды: " + '\n');
                    numberAnimals = in.nextInt();
                    if ((numberAnimals > 0) && (numberAnimals <= packAnimals.size())) {
                        System.out.print("Введите команды, которые выполняет животное, чтобы закончить ввод команд напишите " + "\u001B[34m" + "end" + "\u001B[0m" );
                        temp = in.next();
                        while (!(temp.equals("end"))) {
                            System.out.print("Вводите команды дальше, чтобы закончить ввод команд напишите " + "\u001B[34m" + "end" + "\u001B[0m");
                            instructions.add(temp);
                            temp = in.next();
                        }
                        GetpackAnimals().get(numberAnimals - 1).setInstructions(instructions);
                    } else {
                        System.out.print("Такого животного в таблице НЕТ: " + '\n');
                            }
                    if (optionV == 2) {
                        return;
                    }
                }
                if (option == 4) {
                    return;
                }
            }
        }
    }
}