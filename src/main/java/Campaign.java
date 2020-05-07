public interface Campaign {
    void pause();
    void activate();
    void charge(Click click);

    double remainingBudget();
}
