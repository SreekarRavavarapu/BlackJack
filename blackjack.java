import java.util.Scanner;
public class blackjack
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("BlackJack");
        System.out.println("Everyone starts with a 1000");
        double amount = 1000;


        String [] allpsblts = { "2","2","2","2","3","3","3","3",
                "4","4","4","4", "5","5","5","5", "6","6","6","6", "7","7","7","7",
                "8","8","8","8", "9","9","9","9", "10","10","10","10", "Q", "Q","Q","Q",
                "K","K","K","K", "A","A","A","A", "J", "J" ,"J" , "J"};

        boolean turn = true;

        while (turn)
        {
            System.out.println("-------------------------------------");
            System.out.print("Increase? ");
            double increase = sc.nextInt();
            sc.nextLine();
            System.out.println("Now lets start");

            int dealercnt = 1;
            int playercnt = 1;
            int dealerpnts = 0;
            int playerpnts = 0;


            String player = "dealer";
            dealerpnts = dealerturn(allpsblts, player, dealercnt, dealerpnts);
            dealercnt = dealercnt + 1;

            String random = newcard(allpsblts);
            dealerpnts = calculation(random) + dealerpnts;


            for (int i = 1; i <= 2; i++) {
                player = "player";
                playerpnts = playerturn(allpsblts, player, playercnt, playerpnts);
                playercnt = playercnt + 1;
            }


            System.out.print("If hit type h or stand then type s ");
            String again = "s";
            again = sc.nextLine();

            while (again.equals("h")) {
                player = "player";
                playerpnts = playerturn(allpsblts, player, playercnt, playerpnts);

                playercnt = playercnt + 1;
                System.out.print("If hit type h or stand then type s ");
                again = "s";
                again = sc.nextLine();




            }

            if (again.equals("s")) {
                System.out.println("Dealer will show");
                System.out.println("If the total isn't 17 they will draw cards until it does");

            }

            player = "dealer";
            card(player, random, dealercnt);
            dealercnt = dealercnt +1;



            if (dealerpnts < 17)
            {
                player = "dealer";
                dealerpnts = dealerturn(allpsblts, player, dealercnt, dealerpnts);
                dealercnt = dealercnt + 1;

            }

            int value = result(playerpnts, dealerpnts);

            if (value == 1) {
                amount  = (1.5 * increase) + (amount );
                System.out.println("Balance is " + amount );
            }

            if (value == 2) {
                amount  = amount  - increase;
                System.out.println("Balance is " + amount );
            }

            if (value == 3) {
                amount  = amount  + increase;
                System.out.println("Balance is " + amount );
            }

            if (value == 4) {
                amount  = amount  - increase;
                System.out.println("Balance is " + amount );
            }

            if (value == 5) {
                amount  = amount  - increase;
                System.out.println("Balance is " + amount );
            }

            if (value == 6) {
                amount  = amount ;
                System.out.println("Balance is " + amount );
            }

            if (value == 7) {
                amount  = amount  + increase;
                System.out.println("Balance is " + amount );
            }

            if (value == 8) {
                amount  = amount ;
                System.out.println("Balance is " + amount );
            }

            System.out.print("continue?typer yes or no:  ");
            String temp = sc.nextLine();

            if (amount  > 0 && temp.equals("yes"))
            {
                turn = true;
            }

            else
            {
                turn = false;
            }
        }

        System.out.println("Thank you for playing!");


    }

    public static int dealerturn(String[] allpsblts, String player, int dealercnt, int dealerpnts)
    {
        String randomCard = newcard(allpsblts);
        card(player, randomCard, dealercnt);
        dealerpnts = calculation(randomCard) + dealerpnts;

        return dealerpnts;
    }

    public static int playerturn(String[] allpsblts, String player, int playercnt, int playerpnts)
    {
        String randomCard = newcard(allpsblts);
        card(player, randomCard, playercnt);
        playerpnts = calculation(randomCard) + playerpnts;

        return playerpnts;
    }

    public static String newcard(String allpsblts[])
    {
        int tempvalue = (int)(Math.random() * (allpsblts.length - 1))+1;
        String random = allpsblts[tempvalue];

        arrayHelper.removeMiddle( allpsblts, tempvalue ,allpsblts.length - 1);

        return random;
    }


    public static void card(String player, String random, int cardtotal)
    {
        if (player.equals ("dealer"))
        {
            System.out.println("Your dealer's card #" + cardtotal +": " + random);
        }

        if (player.equals ("player"))
        {
            System.out.println("Your card #" + cardtotal + ": " + random);
        }
    }


    public static int calculation(String random)
    {
        Scanner collectAceValue = new Scanner (System.in);
        int x = 0;
        if (random.equals ("J"))
            x = 10;
        if (random.equals ("2"))
            x = 2;
        if (random.equals ("3"))
            x = 3;
        if (random.equals ("4"))
            x = 4;
        if (random.equals ("5"))
            x = 5;
        if (random.equals ("6"))
            x = 6;
        if (random.equals ("7"))
            x = 7;
        if (random.equals ("8"))
            x = 8;
        if (random.equals ("9"))
            x = 9;
        if (random.equals ("10"))
            x = 10;
        if (random.equals ("K"))
            x = 10;
        if (random.equals ("Q"))
            x = 10;
        if (random.equals ("A"))
        {
            System.out.print("Would you like your Ace to be worth 11 points or 1 point: ");
            x = collectAceValue.nextInt();
            collectAceValue.nextLine();
        }

        return x;

    }

    public static int result(int playerpnts , int dealerpnts)
    {
        int y = 0;
        if (dealerpnts > 21 && playerpnts < 21)
            y = 3;
        else if (playerpnts > 21)
            y = 4;
        else if (playerpnts == 21 && dealerpnts != 21)
            y = 1;
        else if (dealerpnts == 21 && playerpnts != 21)
            y = 2;
        else if (dealerpnts == 21 && playerpnts == 21)
            y = 6;
        else if (dealerpnts <= 21 && playerpnts <= 21 && dealerpnts > playerpnts )
            y = 5;
        else if (dealerpnts <= 21 && playerpnts <= 21 && playerpnts > dealerpnts )
            y = 7;
        else if (dealerpnts == playerpnts)
            y = 8;
        return y;
    }






}

