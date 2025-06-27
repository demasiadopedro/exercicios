# Dados iniciais
nomes     = ['Alice', 'Bruno', 'Carla', 'Daniel', 'Eduarda', 'Fabio']
idades    = [24, 31, 28, 22, 45, 19]
dividas   = [500.0, 1200.0, 800.0, 300.0, 1500.0, 200.0]
inadimpl  = [False, True, False, True, True, False]

todo_inadinplentes= list(map(lambda x: x[0],))
print(todo_inadinplentes)
#1.1. Crie uma lista percentual com o
#  percentual da dívida sobre a renda,di
#  considerando que cada cliente tem renda
#  igual a divida × 2. Use lambda e map.

