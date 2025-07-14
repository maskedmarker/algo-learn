
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