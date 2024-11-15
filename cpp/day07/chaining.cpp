#include <iostream>
#include <list>
using namespace std;

#define SLOTS		10
#define HASH(k)		((k) % SLOTS)

class hashtable;

class entry {
private:
	int roll; // key
	float marks; // value
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
	friend class hashtable;
};

class hashtable {
private:
	list<entry> table[SLOTS];
public:
	float get(int roll) {
		// find the slot for given roll (key)
		int slot = HASH(roll);
		// find the given roll into the bucket (list) in that slot
		list<entry>::iterator itr = table[slot].begin();
		while (itr != table[slot].end()) {
			// if found, return marks (value)
			if (itr->roll == roll)
				return itr->marks;
			itr++;
		}
		// if not found, return -0.0
		return 0.0;
	}
	void put(int roll, float marks) {
		entry e(roll, marks);
		// find the slot for given roll (key)
		int slot = HASH(roll);
		// if bucket is empty, add entry there & return.
		if (table[slot].empty()) {
			table[slot].push_back(e);
			return;
		}
		// find entry for given roll (key) & if found, overwrite value.
		list<entry>::iterator itr = table[slot].begin();
		while (itr != table[slot].end()) {
			if (itr->roll == roll) {
				itr->marks = marks;
				return;
			}
			itr++;
		}
		// if entry is not found, append to the bucket.
		table[slot].push_back(e);
	}
	void display() {
		int i;
		for (i = 0; i < SLOTS; i++) {
			list<entry>::iterator itr = table[i].begin();
			while (itr != table[i].end()) {
				cout << itr->roll << " = " << itr->marks << endl;
				itr++;
			}
		}
	}
};

int main() {
	hashtable ht;
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
	ht.put(69, 609); 
	ht.display();
	return 0;
}
