import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MyArr {
    private Notebook[] arr;
    private int size;
    List<String> list = Arrays.asList("Lenuvo", "Asos", "MacNote", "Eser", "Xamiou");

    public MyArr(int size) {
        this.size = size;
        this.arr = new Notebook[size];
    }

    public void insert() {
        Random random = new Random();
        boolean isRepeat = true;
        boolean isCheck;
        int cost, ram;
        String name;
        for (int i = 0; i < this.size; i++) {
            isCheck = true;
            do {
                cost = 500 + random.nextInt(1550) + ((isRepeat) ? 0 : 1);
                if (cost % 50 == 0) isCheck = false;
            }
            while (isCheck);

            isCheck = true;
            do {
                ram = 4 + random.nextInt(24) + ((isRepeat) ? 0 : 1);
                if (ram % 4 == 0) isCheck = false;
            }
            while (isCheck);


            name = list.get(random.nextInt(list.size()));
            this.arr[i] = new Notebook(cost, ram, name);
        }
    }

    public void display() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.arr[i].getCost() + " " + this.arr[i].getRam() + " " + this.arr[i].getName());
        }
    }

    public void sortSelect() {
        //сортировка по стоимости
        int out, in, mark;
        for (out = 0; out < this.size - 1; out++) {
            mark = out;
            for (in = out + 1; in < this.size; in++) {
                if (this.arr[in].getCost() < this.arr[mark].getCost()) mark = in;
            }
            change(out, mark);
        }

        //сортировка по оперативной памяти
        int markStart =0;
        int markEnd ;
        boolean isCheck;
        for (int i = 0; i < this.size - 1; i++) {
            int j = i + 1;
            markEnd = markStart;
            isCheck = true;
            do {
                if (this.arr[i].getCost() == this.arr[j].getCost()) {
                    markStart = i;
                    markEnd = j;
                    j++;
                    if (j >= this.size) isCheck = false;
                } else isCheck = false;
            } while (isCheck);

            if (markStart != markEnd ) {
                for (int k = markStart; k <= markEnd - 1; k++) {
                    int min = k;
                    for (int l = k + 1; l <= markEnd; l++) {
                        if (this.arr[min].getRam() > this.arr[l].getRam()) {
                            min = l;
                        }
                    }
                    change(k, min);
                }
            }
            if (markEnd + 1 == this.size) break;
        }

        //сортировка по названию
        markStart=0;
        for (int i = 0; i < this.size - 1; i++) {
            int j = i + 1;
            isCheck = true;
            markEnd = markStart;
            do {

                if (this.arr[i].getCost() == this.arr[j].getCost()) {
                    if (this.arr[i].getRam() == this.arr[j].getRam()) {
                        markStart = i;
                        markEnd = j;
                        j++;
                        if (j >= this.size) isCheck = false;
                    } else isCheck = false;
                } else isCheck = false;
            }
            while (isCheck);

            if (markStart != markEnd ) {
                int[] num = new int[markEnd - markStart + 1];
                int index = 0;
                for (int k = markStart; k <= markEnd; k++) {
                    String nameLaptop = this.arr[k].getName();
                    for (int m = 0; m < list.size(); m++) {
                        if (nameLaptop.equals(list.get(m))) {
                            num[index] = m;
                            index++;
                            break;
                        }
                    }
                }
                for (int k = markStart; k < markEnd; k++) {
                    int min = k - markStart;
                    for (int l = k + 1; l <= markEnd; l++) {
                        if (num[min] > num[l - markStart]) {
                            min = l-markStart;
                        }
                    }

                    int tmp = num[k - markStart];
                    num[k - markStart] = num[min];
                    num[min] = tmp;
                    change(k, min+markStart);
                }
            }
            if (markEnd + 1 == this.size) break;
        }
    }


    public void change(int a, int b) {
        Notebook tmp = new Notebook();
        tmp = this.arr[a];
        this.arr[a] = this.arr[b];
        this.arr[b] = tmp;
    }

}
