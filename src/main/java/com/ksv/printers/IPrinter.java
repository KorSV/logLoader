package com.ksv.printers;

import com.ksv.Log;

public interface IPrinter {
    //public void print();

    /*напечатает все что есть в файле*/
    public void printAll();

    /* Напечатает все отсутствующие в БД*/
    public void printMissing();

    /* Напечатает добавленные в файл*/
    public void printAdded(Log newLog);

}
