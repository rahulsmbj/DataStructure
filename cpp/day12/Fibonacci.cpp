#include <iostream>
using namespace std;

int cnt;
	
int recFib(int n) {
	cnt++;
	if(n==1 || n==2)
		return 1;
	return recFib(n-1) + recFib(n-2);
}
	
int memFib(int n, int terms[]) {
	cnt++;
	if(n==1 || n==2) {
		terms[n] = 1;
		return terms[n];
	}
	if(terms[n] != 0)
		return terms[n];
	terms[n] = memFib(n-1, terms) + memFib(n-2, terms);
	return terms[n];
}
	
int dynFib(int n) {
	cnt++;
	int* terms = new int[n+1];
	terms[1] = terms[2] = 1;
	for(int i=3; i <= n; i++)
		terms[i] = terms[i-1] + terms[i-2];
	int res = terms[n];
	delete[] terms;
	return res;
}
	
int main() {
	int n=10, res;
	
	cnt = 0;
	res = recFib(n);
	cout << "Result = " << res << " with fn calls " << cnt << endl;

	int* terms = new int[n+1];
	cnt = 0;
	res = memFib(n, terms);
	cout <<  "Result = " << res << " with fn calls " << cnt << endl;

	cnt = 0;
	res = dynFib(n);
	cout << "Result = " << res << " with fn calls " << cnt << endl;

	return 0;
}
