# clojure 学习

### namespace
* ns 创建名字空间
#### 命名空间和文件路径映射

* 使用 lein 创建目录时，源码根目录默认是 src
* 名字中的横杠映射到文件目录是下划线
* . 分开父目录和子目录

```clojure
(require 'the-divine-cheese-code.visualization.svg)
(refer 'the-divine-cheese-code.visualization.svg)

; 相当于

(use 'the-divine-cheese-code.visualization.svg)
```

### The Evaluator 运算器

像函数，数据结构为参数，使用数据结构相应的规则处理，返回结果。

#### 运算出自身的

```clojure
true
; => true
false
; => false
{}
; => {}
:huzzah
; => :huzzah

()
; => ()
```

#### 符号

分解步骤：

1. 符号名是否是特殊形式，否-2
2. 是否本地绑定，否-3
3. 是否在命名空间里 def 定义，否-4
4. 错误

