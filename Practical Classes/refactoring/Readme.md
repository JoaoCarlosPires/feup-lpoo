# Code Smells and Refactoring

## Example 1

- OrderLine - classe apenas com construtor, sem métodos (Lazy Class)

- Order.isElegibleForFreeDelivery() - tem um if/else a mais no valor de retorno (Dispensable)

- Product.setName e Product.setPrice - nunca são usados (Dispensable - Dead Code)

- O código do Order está sempre a chamar métodos do product da line (Feature Envy)

(Eduardo Correia)

**Dispensable** (Data Class) - `OrderLine` apenas serve para guardar dados 
**Dispensable** (Duplicate Code) - Ciclo `for` em `printOrder()` repetido  
**Dispensable** (Duplicate Code) - `if...else` pode ser convertido num único `return`   
**Couplers** (Feature Envy) - `printOrder()` acede frequentemente aos métodos do atributo `product` de `OrderLine`, podendo-se substituir o que está dentro do `Append()` por um `line.toString()`, resolvendo igualmente o primeiro *code smell referido*. Bem como substituir o cálculo de `total` por um novo método `getTotalPrice()` em `OrderLine` 

## Example 2 

### Code Smell

Object-Orientation Abusers - Switch Statements

### Refactoring

- Criar duas novas classes, ShapeRectangle e ShapeCircle, ambas a extender a classe Shape. Assim, o enum type que definia ou Rectangle ou Circle poderia ser eliminado e não necessitaríamos de ter um switch no construtor. O construtor da classe Shape seria, portanto, um construtor vazio. (Long Parameter List)
 
- As funções getArea() e getPerimeter() passariam a ser definidas dentro destas duas novas classes, passando Shape a ser uma classe abstrata, assim como estes dois métodos nesta classe. (Replace Type Code with Subclasses)

- A função draw() à semelhança da getArea e getPerimeter, deveria passar a ser abstract e definida de acordo com a shape em cada classe respetiva, ShapeRectangle e ShapeCircle. (Replace Type Code with Subclasses)

- Neste caso, os testes teriam de ser alterados, na medida em que a inicialização de um Retângulo ou de um Círculo teria de ser feita de forma diferente da default. Ou seja, sempre que aparece algo do género: Shape rectangle = new Shape(5, 5, 10, 20); Deve passar a ser: Shape rectangle = new ShapeRectangle(5, 5, 10, 20); E o mesmo para circle.

(Eduardo Correia)

**Dispensables** Comentários excessivos nos atributos da classe Shape
**Bloaters (Large Class)** A classe Shape viola vários princípios SOLID e por ser uma Large Class dificulta a sua expansão e manutenção, logo, seria melhor criar as classes Rectangle e Circle, derivadas de Shape e esta passava a ser abstrata

## Example 3 (Switch Statements - Replace Type Code with Subclasses)

- Criar duas classes DiscountFixed e DiscountPercentage e assim eliminar os dois construtores, passando Discount a ser uma classe abstrata, sem construtor e com o método applyDiscount abstrato, sendo implementado em cada uma destas novas classes que a extendem. Em cada uma destas classes, os atributos fixed e percentage deixam de ser finais, sendo que o atributo fixed é eliminado de DiscountFixed e o atributo percentagem é eliminado de DiscountPercentage.

- As funções applyDiscount de ambas as classes poderiam ser simplificadas, eliminando a variável de retorno e os if/else, passando a ter apenas um if com retorno e outro retorno, que só ocorrerá no caso 'else', que neste caso não precisa de estar escrito visto estarmos a lidar com return statements.

- Na função getTotal, da classe SimpleOrder, alterar o if statement com o NULL, usando para isso o Refactoring Introduce Null Object.

(João Rocha)

Não seria mais simples separar a classe Discount em DiscountFixed e DiscountPercentange? Andar com fixed = 0 ou percentage = 0 não me parece a solução mais clara, especialmente para uma futura manutenção.

(Eduardo Correia)

**Object-Orientation Abusers (Switch Statements -> Extract Class)** O if...else em ApplyDiscount pode ser simplificado, retirando o else desnecessário
**Dispensable (Dead Code)** É possivel remover o *assignment* de discountedPrice, executando de logo o return em vez de igualar a uma variável de "resultado"

## Example 4

- Uma vez que os Clients não têm dados relativos ao login, não faz sentido estarem a extender a classe Worker.

- A solução pode passar por criar uma nova super-classe Person, ficando as classes Client e
Worker a extender esta nova classe, que tem os métodos de getName() e getPhone(). 

## Example 5

- O método execute da classe Turtle é demasiado longo, podendo ser simplificado aplicando 3x o refactoring Extract Method e eliminando assim também os comentários.

- Tanto a direcção como o comando da Tartaruga podem ser passados a objectos criando duas classes respectivas.

- Os métodos moveForward(), rotateLeft() e rotateRight() têm demasiados ifs statements. De forma a simplificar tal situação, optei pelas seguintes alterações:

    - Criação de quatro novas classes, cada uma respectiva a uma direção da Tartaruga (norte, sul, este e oeste). Cada uma destas classes implementa os três métodos acima descritos, métodos estes presentes na interface que cada uma destas classes implementa.

    - Os métodos rotateLeft() e rotateRight() retornam um novo tipo de tartaruga que será guardado no atributo turtle da classe Turtle. Ou seja, imaginemos que a tartaruga está na posição norte (TurtleNorth), esta posição é a guardada nesse atributo. Ao fazer uma rotação para a esquerda, a tartaruga passa a estar na posição oeste, sendo uma nova instância da classe TurtleWest criada e devolvida na função rotateLeft, passando a ser o valor do atributo turtle na classe Turtle.

## Example 6

- Os atributos locationLatitude, locationLongitude e locationName da classe Tree são usados sempre em conjunto. Assim, podemos passar os três atributos para dentro de uma nova classe, chamada, p.e. Location, com os respectivos getters e setters e tratar os atributos como um só objecto Location na classe Tree.

- O método isNextAppraisalOverdue() da classe Tree é demasiado longo. A solução para corrigir é fazer duas vezes o refactoring Extract Methods, fazendo com que o método seja mais curto e não necessite dos comentários.

