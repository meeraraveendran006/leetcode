class Solution {
public:
    ListNode* oddEvenList(ListNode* head) {
        if (head == nullptr || head->next == nullptr) {
            return head;
        }

        ListNode* odd = head;
        ListNode* even = head->next;
        ListNode* evenHead = even;

        // Ensure even and even->next are both non-null
        while (even != nullptr && even->next != nullptr) {
            odd->next = even->next;
            odd = odd->next;

            even->next = odd->next;
            even = even->next;
        }

        // Attach the start of the even list to the end of the odd list
        odd->next = evenHead;

        return head;
    }
};