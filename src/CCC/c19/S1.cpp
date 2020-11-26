#include <iostream>
#include <string>

using namespace std;
int main(void){
    string line;
    getline(cin,line);

    int hCount=0;
    int vCount=0;

    for (char c:line){
        if (c=='H'){
            hCount++;
        }else{
            vCount++;
        }
    }

    hCount%=2;
    vCount%=2;

    if (hCount == 0 && vCount == 0) {
        cout<<"1 2"<<endl;
        cout<<"3 4"<<endl;
    } else if (hCount == 0 && vCount == 1) {
        cout<<"2 1"<<endl;
        cout<<"4 3"<<endl;
    } else if (hCount == 1 && vCount == 0) {
        cout<<"3 4"<<endl;
        cout<<"1 2"<<endl;
    } else {
        cout<<"4 3"<<endl;
        cout<<"2 1"<<endl;
    }

    return 0;
}
