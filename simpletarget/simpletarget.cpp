#include <iostream>

using namespace std;

int main() {
    int n;
    cin >> n;
    switch (n) {
        case 1:
            cout << "one" << endl;
            break;
        case 42:
            cout << "the answer";
            break;
        default:
            return 1;
    }
    return 0;
}