#include <iostream>
using namespace std;

#define SLOTS		10
#define HASH(k)		((k) % SLOTS)
#define REHASH(i)	(((i)+1) % SLOTS)

class oa_hashtable;

class entry {
private:
	int roll;
	float marks;
public:
	entry() {
		roll = 0;
		marks = 0.0;
	}
	entry(int r, float m) {
		roll = r;
		marks = m;
	}
	bool is_empty() {
		return roll == 0;
	}
	friend class oa_hashtable;
};

class oa_hashtable {
private:
	entry table[SLOTS];
public:
	
	float get(int roll) {
		int slot = HASH(roll);
		if (table[slot].is_empty())
			return 0.0f; // not found
		if (table[slot].roll == roll) // found
			return table[slot].marks;
		for (int i = 0; i < SLOTS && !table[slot].is_empty(); i++) {
			slot = REHASH(slot);
			if (!table[slot].is_empty() && table[slot].roll == roll) // found
				return table[slot].marks;
		}
		return 0.0f; // not found
	}

	void put(int roll, float marks) {
		entry ne(roll, marks);
		int i, slot = HASH(roll);
		if (table[slot].is_empty()) {
			table[slot] = ne; // empty slot found
			return;
		}
		if (table[slot].roll == roll) { // found
			table[slot] = ne; // overwrite the slot (value)
			return;
		}
 		for (i = 0; i < SLOTS && !table[slot].is_empty(); i++) {
			slot = REHASH(slot);
			if (table[slot].is_empty()) {
				table[slot] = ne; // empty slot found
				return;
			}
			if (table[slot].roll == roll) { // found
				table[slot] = ne; // overwrite the slot (value)
				return;
			}
		}
		throw "all slots full";
	}

	void display() {
		int i;
		for (i = 0; i < SLOTS; i++) {
			if (!table[i].is_empty())
				cout << "roll=" << table[i].roll << ", marks=" << table[i].marks << endl;
		}
	}
};

int main() {
	oa_hashtable ht;
	try {
		ht.put(11, 101);
		ht.put(22, 202);
		ht.put(33, 303);
		ht.put(21, 201);
		ht.put(41, 401);
		ht.put(52, 502);
		ht.put(61, 601);
		ht.put(93, 903);
		ht.put(11, 111); // overwrite
		ht.put(22, 222); // overwrite
		ht.put(46, 406);
		ht.put(75, 705);
		ht.put(61, 611); // overwrite
		//ht.put(69, 609); // full 
		ht.display();

		int roll;
		cout << "enter roll: ";
		cin >> roll;
		float marks = ht.get(roll);
		if (marks == 0.0f)
			cout << "marks not found." << endl;
		else
			cout << "marks: " << marks << endl;
	}
	catch (...) {
		cout << "hashtable is full" << endl;
	}
	return 0;
}
