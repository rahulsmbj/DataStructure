#include <iostream>
using namespace std;

class SinglyList;
class Node {
private:
	int data;
	Node* next;
	
	Node(int val=0) {
		data = val;
		next = NULL;
	}

	friend class SinglyList;
};

class SinglyList {
private:
	Node *head;

public:
	SinglyList() {
		head = NULL;
	}

	// time complexity - O(n)
	void addLast(int val) {
		Node *newNode = new Node(val);
		// special case: if list is empty
		if(head == NULL)
			head = newNode; // newNode itself is first node of list
		else { // general case: if list already have few nodes
			// traverse till last node
			Node *trav = head;
			while(trav->next != NULL)
				trav = trav->next;
			// add address of newNode into last node's next
			trav->next = newNode;
		}
	}
	
	// time complexity - O(n)
	void display() {
		cout << "List: ";
		Node *trav = head;
		while(trav != NULL) {
			cout << trav->data << ", ";
			trav = trav->next;
		}
		cout << endl;
	}
	
	void selectionSort() {
		for(Node *i=head; i != NULL; i = i->next) {
			for(Node *j=i->next; j != NULL; j = j->next) {
				if(i->data > j->data) {
					int temp = i->data;
					i->data = j->data;
					j->data = temp;
				}
			}
		}
	}
	
	void reverse() {
		Node *oldhead = head;
		head = NULL;
		while(oldhead != NULL) {
			Node *temp = oldhead;
			oldhead = oldhead->next;
			temp->next = head;
			head = temp;
		}
	}
	
	void reverseDisplay(Node *trav) {
		if(trav == NULL)
			return;
		reverseDisplay(trav->next);
		cout << trav->data << ", ";
	}
	
	void reverseDisplay() {
		cout << "Rev Display: ";
		reverseDisplay(head);
		cout << endl;
	}
	
	void recReverse(Node *trav) {
		if(trav->next == NULL) {
			head = trav;
			return;
		}
		recReverse(trav->next);
		trav->next->next = trav;
		trav->next = NULL;
	}
	
	void recReverse() {
		if(head != NULL)
			recReverse(head);
	}
	
	int findMiddle() {
		Node *fast = head, *slow = head;
		while(fast != NULL && fast->next != NULL) {
			slow = slow->next;
			fast = fast->next->next;
		}
		return slow != NULL ? slow->data : 0;
	}
};

int main() {
	SinglyList l1;
	l1.addLast(30);
	l1.addLast(10);
	l1.addLast(40);
	l1.addLast(20);
	l1.display();

	l1.selectionSort();
	l1.display();
	l1.reverse();
	l1.display();
	l1.reverseDisplay();
	
	l1.display();
	l1.recReverse();
	l1.display();
	
	l1.addLast(50);
	l1.display();
	int res = l1.findMiddle();
	cout << "Middle : " << res << endl;

	return 0;
}








