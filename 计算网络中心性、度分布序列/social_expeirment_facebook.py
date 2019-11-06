import networkx as nx
import pandas as pd
import matplotlib.pyplot as plt

def readNetwork(filename):

    edges = pd.read_table(filename, header=None,delim_whitespace=True)
    #read_edgelists

    for i in range(len(edges)):
        G.add_edge(edges.iloc[i,0],edges.iloc[i,1])

def sortedDictValues1(adict):
    keys = list(adict.keys())
    print(type(keys))
    keys.sort()
    print(keys)
    return [adict[key] for key in keys]

#创建图
G = nx.Graph()
readNetwork("F:\\社交挖掘\\facebook\\0_edges.txt")
print("--------")
# nx.draw(G)
# plt.draw()
#计算中心性

betweenness_centrality = nx.betweenness_centrality(G)
betweenness_centrality= sorted(betweenness_centrality.items(), key=lambda d:d[0])
print(type(betweenness_centrality))
# betweenness_centrality = sortedDictValues1(betweenness_centrality)
# betweenness_centrality = sorted(betweenness_centrality.items(), key=lambda d: d[1], reverse=True)

# in_degree_centrality =  nx.out_degree_centrality(G)
# out_degree_centrality =  nx.in_degree_centrality(G)
degree_centrality =  nx.degree_centrality(G)
degree_centrality = sorted(degree_centrality.items(), key=lambda d:d[0])
# degree_centrality = sorted(degree_centrality.items(), key=lambda item: item[1], reverse=True)

eigenvector_centrality =  nx.eigenvector_centrality(G)
eigenvector_centrality = sorted(eigenvector_centrality.items(), key=lambda d:d[0])
# degree_centrality = sorted(degree_centrality.items(), key=lambda item: item[1], reverse=True)


print("betweenness_centrality: ", betweenness_centrality)
print("degree_centrality: ", degree_centrality)
print("eigenvector_centrality: ", eigenvector_centrality)



output = open('data1.txt','w',encoding='gbk')
for row in betweenness_centrality:
    row =list(row)
    rowtxt = '{}'.format(row)
    output.write(rowtxt)
    output.write('\n')

output = open('data2.txt','w',encoding='gbk')
for row in degree_centrality:
    row =list(row)
    rowtxt = '{}'.format(row)
    output.write(rowtxt)
    output.write('\n')

output = open('data3.txt','w',encoding='gbk')
for row in eigenvector_centrality:
    row =list(row)
    rowtxt = '{}'.format(row)
    output.write(rowtxt)
    output.write('\n')


#计算节点度分布
print("所有节点的度分布序列",nx.degree_histogram(G))
# #绘制无向图度度分布曲线
degree=nx.degree_histogram(G)#返回图中所有节点的度分布序列
#
x=range(len(degree))#生成X轴序列，从1到最大度
y=[z/float(sum(degree))for z in degree]#将频次转化为频率，利用列表内涵
plt.loglog(x,y,color="blue",linewidth=2)#在双对坐标轴上绘制度分布曲线
plt.show()#显示图表

# https://networkx.github.io/documentation/stable/reference/algorithms/centrality.html