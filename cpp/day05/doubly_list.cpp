#include <iostream>
using namespace std;

class doubly_list; // forward declaration class

class node {
private:
	int data;
	node* next;
	node* prev;
public:
	node() {
		data = 0;
		next = NULL;
		prev = NULL;
	}
	node(int val) {
		data = val;
		next = NULL;
		prev = NULL;
	}
	friend class doubly_list;
};

class doubly_list {
private:
	node* head;
public:
	doubly_list() {
		head = NULL;
	}
	void display() {
		cout << "FWD LIST: ";
		node* trav = head;
		while (trav != NULL) {
			cout << trav->data << ", ";
			trav = trav->next;
		}
		cout << endl;
	}
	void display_rev() {
		cout << "REV LIST: ";
		// traverse till last node
		node* trav;
		if (head != NULL) {
			trav = head;
			while (trav->next != NULL)
				trav = trav->next;
			// visit each node in reverse order (using prev pointer)
			while (trav != NULL) {
				cout << trav->data << ", ";
				trav = trav->prev;
			}
		}
		cout << endl;
	}
	void add_first(int val) {

	}
	void add_last(int val) {
		node* newnode, * trav;
		// alloc new node and init it
		newnode = new node(val);
		// special case: if list is empty, newnode itself is first node
		if (head == NULL)
			head = newnode;
		else {
			// traverse till last node
			trav = head;
			while (trav->next != NULL)
				trav = trav->next;
			// add new node to the next of last node
			trav->next = newnode;
			// newnode prev to last node
			newnode->prev = trav;
		}
	}
	void add_at_pos(int pos, int val) {

	}
	void del_first() {
	}

	void del_at_pos(int pos) {
	}

	~doubly_list() {
		del_all();
	}

	void del_all() {
		while (head != NULL)
			del_first();
	}
};

int main() {
	doubly_list l1;
	l1.add_last(10);
	l1.add_last(20);
	l1.add_last(30);
	l1.add_first(40);
	l1.add_at_pos(3, 50);
	l1.display();
	l1.add_at_pos(1, 60);
	l1.display();
	l1.add_at_pos(7, 70);
	l1.display();
	l1.del_first();
	l1.display();
	l1.del_first();
	l1.display();
	l1.del_at_pos(3);
	l1.display();
	l1.del_at_pos(4);
	l1.display();
	l1.del_at_pos(5);
	l1.display();
	l1.del_all();
	l1.display();
	l1.display_rev();
	return 0;
}




