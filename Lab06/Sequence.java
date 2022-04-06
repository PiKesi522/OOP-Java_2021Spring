class SequenceItem{
    private String Data;
    public SequenceItem(String s){
        this.Data = s;
    }
    public String getData(){
        return this.Data;
    }
    public void setData(String s){
        this.Data = s;
    }
}

interface Sequence{
    boolean contains(SequenceItem item);
    boolean equals(Sequence seq);
    boolean isEmpty();
    int size();
    void add(SequenceItem item);
    void remove(SequenceItem item);
    SeqIterator Iterator();
    SeqIterator reverseIterator();
    SeqIterator biIterator();
    SequenceItem get(int i);
    SequenceItem[] ToArray();
    String Tostring();
}

interface SeqIterator{
    boolean hasNext();
    void remove();
    SequenceItem next();
}

interface SeqBiIterator extends SeqIterator{
    boolean hasPrevious();
    SequenceItem previous();
}

class ArraySequence implements Sequence{
    private int size = 10;
    private int used = 0;   // Store how much space has been used to modify the size of the Array
    private SequenceItem[] Items = new SequenceItem[size];
    public ArraySequence(){
        this.size = 10;
        this.used = 0;
        this.Items = new SequenceItem[10];
    }
    protected void resize(){  // Use this function In every function which changes the used space
        if(used + 1 == size){   // If the container is about to be fully used
            this.size *= 2;     // We double the space the Array need.
            SequenceItem[] temp = new SequenceItem[size];
            for(int i = 0; i < used; i++){
                temp[i] = this.Items[i];
            }
            this.Items = temp;
        }
        else if(used * 4 < size){   // If the container has too much free space
            this.size /= 2;     // We shorten the Array
            SequenceItem[] temp = new SequenceItem[size];
            for(int i = 0; i < used; i++){
                temp[i] = this.Items[i];
            }
            this.Items = temp;
        }
    }
    public boolean contains(SequenceItem item){
        SequenceItem target = item;
        for(int i = 0; i < used; i++){
            if(this.Items[i].equals(target))
                return true;
        }
        return false;
    };
    public boolean equals(Sequence seq){
        if(this.used != seq.size())
            return false;
        for(int i = 0; i < used; i++){
            if(!this.Items[i].equals(seq.get(i+1))) {
                return false;
            }
        }
        return true;
    };
    public boolean isEmpty(){
        return (this.used == 0);
    };
    public int size(){
        return this.used;
    };
    public void add(SequenceItem item){
        this.Items[used++] = item;
        resize();
    };
    public void remove(SequenceItem item){
        SequenceItem target = item;
        if(!this.contains(target)){
            System.out.println("Not Found Such Item");
            return;
        }
        int loc = 0;
        while(!this.get(++loc).equals(target)); // Where is the element?
        for(int j = loc - 1; j < used - 1; j++){  // Postpone the latter to the former
            this.Items[j] = this.Items[j + 1];
        }
        this.Items[used--] = null;  // Clear the last Item
        resize();
    };
    public SeqIterator Iterator(){
        // We need to create another inner class to implement the interface
        return new PIterator();
    };
    protected class PIterator implements SeqIterator{
        protected int loc = 0;  // It will be used in Subclass
        public PIterator(){  // Constructor function
            this.loc = 0;
        }
        // Identify a Innerclass to implement the iterator
        @Override
        public boolean hasNext() {
            return (this.loc < ArraySequence.this.used);
        };
        @Override
        public SequenceItem next() {
            return ArraySequence.this.Items[loc++];
        }
        @Override
        public void remove() {
            SequenceItem target = ArraySequence.this.Items[--loc];
            ArraySequence.this.remove(target);
            // In method:Remove, method:resize will be used at the same time
        };
    }
    public SeqIterator reverseIterator(){
        return new PreverseIterator();
    };
    protected class PreverseIterator implements SeqIterator{
        protected int loc;
        public PreverseIterator(){  // Constructor function
            this.loc = ArraySequence.this.used - 1;
        }
        // Identify a Innerclass to implement the iterator
        @Override
        public boolean hasNext() {
            return (this.loc > 0);
        };
        @Override
        public SequenceItem next() {
            return ArraySequence.this.Items[loc--];
        }
        @Override
        public void remove() {
            SequenceItem target = ArraySequence.this.Items[++loc];
            ArraySequence.this.remove(target);
        };
    }
    public SeqIterator biIterator(){
        return new PSeqIterator();
    };
    protected class PSeqIterator extends PIterator implements SeqBiIterator{
        // We inherit used Inner class:PIterator
        @Override
        public SequenceItem previous(){
            if(this.hasPrevious())
                return ArraySequence.this.Items[--this.loc];
            else{
                System.out.println("No more element!");
                return null;
            }
        }
        @Override
        public boolean hasPrevious() {
            return this.loc > 0;
        }
    };
    public SequenceItem get(int i){
        return this.Items[i-1];
    };
    public SequenceItem[] ToArray(){
        SequenceItem[] seq = new SequenceItem[used];
        for(int i = 0; i < used; i++){
            seq[i] = this.Items[i];
        }
        return seq;
    };
    public String Tostring(){
        SequenceItem[] seq = this.ToArray();
        String s = new String();
        for(int i = 0; i < used; i++){
            s += i + ": " + seq[i].getData() + ".\n";
        }
        return s;
    };
}

class IteratorTest{
    public static void display(ArraySequence.PIterator i){
        while(i.hasNext()){
            SequenceItem target = i.next();
            System.out.println(target.getData());
        }
    }
}

class Node{
    public SequenceItem data;
    public Node next;
    public Node(SequenceItem It){
        setItem(It);
        setNext(next);
    }
    public void setItem(SequenceItem It){
        this.data = It;
    }
    public void setNext(Node next){
        this.next = next;
    }
}

class LinkedSequence implements Sequence{
    private Node Head;      // Use Node to store data
    private int used;       // Store how many data have been used
    public LinkedSequence(){
        this.Head = new Node(null);
    }
    public boolean contains(SequenceItem item){
        SequenceItem target = item;
        Node temp = this.Head;
        while(temp.next != null){
            if(temp.data.equals(item)) {
                return true;
            }
            else{
                temp = temp.next;     // Goto the next node
            }
        }
        if(temp.data != item)   // Thea last data won't be
            return false;       // test in the recrusion above
        else
            return true;
    };
    public boolean equals(Sequence seq){
        if(this.used != seq.size()){
            return false;
        }
        Node temp = this.Head;
        int i = 0;
        while(temp.next != null){
            if(temp.data != seq.get(++i)){
                return false;
            }
            temp = temp.next;
        }
        return (temp.data == seq.get(used));
    };
    public boolean isEmpty(){
        return (this.used == 0);
    };
    public int size(){
        return this.used;
    };
    public void add(SequenceItem item){
        Node temp = this.Head;
        while(temp.next != null){
            temp = temp.next;
        }
        Node newone = new Node(item);
        temp.next = newone;
    };
    public void remove(SequenceItem item){
        if(!this.contains(item)){
            System.out.println("Not Found Such Item");
            return;
        }
        Node pre = this.Head;
        Node temp = this.Head;
        while(temp.next != null){
            if(temp.data == item){
                if(pre == this.Head){
                    this.Head = this.Head.next;
                }   // The first element is the target, just skip it
                else{
                    pre.next = temp.next;
                }   // Repoint the previous node to the next one
                return;
            }
            pre = temp;
            temp = temp.next;
        }
        // Since there must be a item in the Node
        // Then the last one must be the target
        // when the recrusion end
        pre.next = null;
        return;
    };
    public SeqIterator Iterator(){
        // We need to create another inner class to implement the interface
        return new LinkedSequence.PIterator();
    };
    protected class PIterator implements SeqIterator{
        protected Node pointer;  // It will be used in Subclass
        public PIterator(){  // Constructor function
            this.pointer = LinkedSequence.this.Head;
        }
        // Identify a Innerclass to implement the iterator
        @Override
        public boolean hasNext() {
            return (this.pointer.next != null);
        };
        @Override
        public SequenceItem next() {
            pointer = pointer.next;
            return this.pointer.data;
        }
        @Override
        public void remove() {
            if(this.hasNext()){
                // When the present location is cleared
                // the pointer will move to the next location
                Node temp = this.pointer.next;
                LinkedSequence.this.remove(pointer.data);
                this.pointer = temp;
            }
            else{
                // Otherwise, it will be moved to the previous location
                Node temp = LinkedSequence.this.Head;
                while(temp.next != this.pointer){
                    temp = temp.next;
                }
                temp.next = null;
                this.pointer = temp;
            }
        };
    }
    public SeqIterator reverseIterator(){
        return new LinkedSequence.PreverseIterator();
    };
    protected class PreverseIterator implements SeqIterator{
        protected Node Repointer = LinkedSequence.this.Head;
        public PreverseIterator(){  // Constructor function
            while(this.Repointer.next != null){
                this.Repointer = this.Repointer.next;
            }
        }
        // Identify a Innerclass to implement the iterator
        @Override
        public boolean hasNext() {
            return (this.Repointer != LinkedSequence.this.Head);
        };
        @Override
        public SequenceItem next() {
            Node temp = LinkedSequence.this.Head;
            while(temp.next != this.Repointer){
                temp = temp.next;
            }
            this.Repointer = temp;
            return this.Repointer.data;
        }
        @Override
        public void remove() {
            if(this.hasNext()){
                // When the present location is cleared
                // the pointer will move to the previous node
                Node temp = LinkedSequence.this.Head;
                while (temp.next != this.Repointer){
                    temp = temp.next;
                }
                temp.next = Repointer.next;
                this.Repointer = temp;
            }
            else{
                // Otherwise, it will be moved to the next location
                this.Repointer = LinkedSequence.this.Head.next;
            }
        };
    }
    public SeqIterator biIterator(){
        return new LinkedSequence.PSeqIterator();
    };
    protected class PSeqIterator extends LinkedSequence.PreverseIterator implements SeqBiIterator{
        // We inherit used Inner class:PIterator
        @Override
        public SequenceItem previous(){
            return this.next();
        }
        @Override
        public boolean hasPrevious() {
            return (this.Repointer != LinkedSequence.this.Head);
        }
    };
    public SequenceItem get(int i){
        i -= 1;
        Node temp = this.Head;
        while(i-- != 0){        // To search a certain data in LinkedArray
            temp = temp.next;   // We can only search them in order
        }
        return temp.data;
    };
    public SequenceItem[] ToArray(){
        SequenceItem[] seq = new SequenceItem[used];
        Node temp = this.Head;
        for(int i = 0; i < used; i++){
            seq[i] = temp.data;
            temp = temp.next;
        }
        return seq;
    };
    public String Tostring(){
        SequenceItem[] seq = this.ToArray();
        String s = new String();
        for(int i = 0; i < used; i++){
            s += i + ": " + seq[i].getData() + ".\n";
        }
        return s;
    };
}

// end