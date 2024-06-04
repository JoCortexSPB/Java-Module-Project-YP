import java.util.*;

public class Main {
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_GREY = "\u001B[37m";

    public static void main(String[] args) {
        System.out.println(COLOR_BLUE + "\n2024 () Калькулятор счёта, версия 1.02 (обновление 04.06.2024 г.)");
        System.out.println(COLOR_GREY + "Автор: Поляков Евгений (tpda.oncore@yandex.ru)\n" + COLOR_RESET);
        System.out.println("Укажите количество людей, между которыми будет поделён счёт?");
        int guests = guestsCounter();

        System.out.println("Давайте заполним таблицу товаров и их стоимости.");
        ArrayList<GoodItem> list = goodsCollect();

        calculate(list, guests);
    }

    public static String rubFormatter(Double rubles) {
        if ((Math.floor(rubles) % 100) == 1) {
            return " рубль";
        } else if ((Math.floor(rubles) % 100) > 1 && ((Math.floor(rubles % 100)) < 5)) {
            return " рубля";
        } else {
            return " рублей";
        }
    }

    public static void calculate(ArrayList<GoodItem> list, int guests) {
        System.out.println("Список добавленных товаров:");
        Iterator<GoodItem> itemIterator = list.iterator();
        int counter = 0;
        Double sum = 0.00;
        while (itemIterator.hasNext()) {
            GoodItem item = itemIterator.next();
            System.out.println(String.format(Locale.US, "%15s \t\t %.2f", item.name, item.cost) + rubFormatter(item.cost));
            counter++;
            sum += item.cost;
        }
        System.out.println();
        System.out.println(String.format(Locale.US, COLOR_GREEN + "Общий счёт: %.2f%s" + COLOR_RESET, sum, rubFormatter(sum)));
        System.out.println(String.format(Locale.US, "Каждый человек (а их всего %d) должен уплатить по " + COLOR_GREEN + "%.2f" + COLOR_RESET + "%s", guests, sum / guests, rubFormatter(sum / guests)));
    }

    public static ArrayList<GoodItem> goodsCollect() {
        ArrayList<GoodItem> list = new ArrayList<>();
        while (true) {
            System.out.println("Для продолжения введите название товара. Что прервать ввод товаров введите 'Завершить'");
            String tempName = getLine();
            if (tempName.equalsIgnoreCase("Завершить")) {
                return list;
            }
            System.out.println("Стоимость товара? ");
            Double tempCost = getDouble();

            list.add(new GoodItem(tempName, tempCost));
        }
    }

    public static Double getDouble() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
                Double cost = scanner.nextDouble();
                if (cost >= 0.00) {
                    return cost;
                } else {
                    System.out.println(COLOR_RED + "Ошибка: " + COLOR_RESET + " значение не может быть меньше 0.00.");
                }
            } catch (NumberFormatException e) {
                System.out.println(COLOR_RED + "Ошибка: " + COLOR_RESET + " неверный формат ввода (" + e.getMessage() + ")");
            }
        }
    }

    public static String getLine() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    return name;
                } else {
                    System.out.println(COLOR_RED + "Ошибка: " + COLOR_RESET + " значение не должно быть пустым.");
                }
            } catch (Exception e) {
                System.out.println(COLOR_RED + "Ошибка: " + COLOR_RESET + " неверный формат ввода.");
            }
        }
    }

    public static int guestsCounter() {
        while (true)
            try {
                Scanner scanner = new Scanner(System.in);
                int temp = scanner.nextInt();
                if (temp < 2) {
                    System.out.println(COLOR_RED + "Ошибка: " + COLOR_RESET + " необходимо ввести число больше 1.");
                } else {
                    return temp;
                }
            } catch (Exception e) {
                System.out.println(COLOR_RED + "Ошибка: " + COLOR_RESET + " Необходимо ввести значение из множества натуральных чисел.");
            }
    }
}