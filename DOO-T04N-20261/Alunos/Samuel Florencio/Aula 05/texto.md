#Paradigmas de Programação: Imperativo e Declarativo.

##**PARADIGMA IMPERATIVO**
É um tipo de paradigma de progamação que descreve como o programa é executado. Os desenvolvedores estão mais preocupados em como obter a resposta passo a passo.Ela é ótima para processos em circuito fechado e oferece uma maneira direta de personalizar código e funções. Infelizmente, grandes edições no código podem levar a uma alta probabilidade de erros, por isso revisões frequentes do código são indispensáveis.
Linguagens de programação Fortran, Java, C e C++ são exemplos de programação imperativa.

##**PARADIGMA DECLARATIVO**
É um tipo de paradigma de programação que descreve quais programas devem ser executados. Os desenvolvedores estão mais preocupados com a resposta recebida. Ele declara que tipo de resultado queremos e deixa a linguaguem de programação de lado, focando apenas em descobrir como produzi-los. A complexa sintaxe da programação declarativa, no entando, pode dificultar a personalização do código de recursos e exige muita especificidade para executar funções complicadas. Em palavara simples, ele foca principalmente no resultado final.
Linguagens de programação Miranda, Erlang, Haskell, Prolog são alguns exemplos populares de programação declarativa.  

##**COMPARAÇÃO**
CÓDIGO **JAVA** - IMPERATIVO

```java
public class Num{
    public static void main (String[] args){
        Arraylist menores = new Arraylist(); 
        for (int i = 0; i<20; i++){      
//aqui é criado um laço repetitivo que vai percorrer de 0 a 19.
        }
        if(i<6){
           menores.add(i);
        }
//é criado uma condição antes de chegar no número final, que declara que ira adicionar apenas os números menores que 6.
        System.out.println(menores);
//exibe o resultado.
     }
}
```
 
 O CÓDIGO EM JAVA MOSTRA QUE É NECESSÁRIO CONSTRUIR CONDIÇÕES EM UMA SEQUÊNCIA PARA PODER TER O RESULTADO.
 
 
 ---
 
 
CÓDIGO **PROLOG** - DECLARATIVO
 
```PROLOG
menor(x) :-
       member(X,[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19]),
 %gera cada elemento da lista como possivel x.
   X<6 % filtra apenas os menores que 6.
?- menor(X).
%aqui vai consultar todos os números menores que 6.
```

O CÓDIGO EM PROLOG MOSTRA AS REGRAS E QUE VOCÊ PODE CHEGAR EM UM RESULTADO SEM QUE SEJA NECESSÁRIO MONTAR UM PROCESSO E EXECUTALO.
   
      