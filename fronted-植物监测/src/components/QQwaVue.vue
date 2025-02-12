<template>
  <div>
    <!-- 按钮，点击后显示图表 -->
    <button @click="showChart = true">显示图表</button>
    
    <!-- 控制图表显示的部分 -->
    <div v-if="showChart" ref="chart" style="height: 400px; width: 100%"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  data() {
    
    return {
      showChart: false, // 控制图表是否显示
      historyData: [
        { timestamp: '2025-01-01', value: 10 },
        { timestamp: '2025-01-02', value: 15 },
        { timestamp: '2025-01-03', value: 20 },
      ],
      chartInstance: null, // 存储 ECharts 实例
    };
  },
  methods: {
    renderChart() {
      const chartElement = this.$refs.chart;
      alert(chartElement);
      if (chartElement) {
        // 如果已有实例，销毁它
        if (this.chartInstance) {
          this.chartInstance.dispose();
        }
        
        // 创建新的 ECharts 实例
        this.chartInstance = echarts.init(chartElement);
        const data = this.historyData.map(item => ({
          timestamp: item.timestamp,
          value: item.value,
        }));
        const option = {
          xAxis: {
            type: 'category',
            data: data.map(item => item.timestamp),
          },
          yAxis: {
            type: 'value',
          },
          series: [{
            data: data.map(item => item.value),
            type:"bar", // 折线图
            
          }],
        };
        this.chartInstance.setOption(option);
      }
    },
  },
  watch: {
    showChart(newVal) {
      // 当 showChart 为 true 时调用 renderChart
      if (newVal) {
        this.$nextTick(() => {
          this.renderChart();
        });
      }
    },
  },
};
</script>

<style scoped>
/* 如果需要，添加样式 */
</style>
