
```text
def preorder_traversal(root):
    if not root:
        return []
    stack = [root]
    result = []
    while stack:
        node = stack.pop()
        result.append(node.val)
        if node.right:
            stack.append(node.right)
        if node.left:
            stack.append(node.left)
    return result
```

```text
def inorder_traversal(root):
    stack = []
    result = []
    curr = root
    while curr or stack:
	    // 找到当前树root的最左最深子树root
        while curr:
            stack.append(curr)
            curr = curr.left
        curr = stack.pop()
        result.append(curr.val)
		// curr当作是树的root
        curr = curr.right
    return result
```

```text
def postorder_traversal(root):
    if not root:
        return []
    stack1 = [root]
    stack2 = []
    result = []
    while stack1:
        node = stack1.pop()
        stack2.append(node)
        if node.left:
            stack1.append(node.left)
        if node.right:
            stack1.append(node.right)
    while stack2:
        result.append(stack2.pop().val)
    return result
```