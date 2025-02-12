<template>
  <div>
    <h2 v-if="username" class="welcome-text">
      <span id="welcome-text-part">Welcome, </span>
      <span id="username-text">{{ username }}</span>
    </h2>


    <div id="radar">
    <div 
      v-for="crop in radarStatuses" 
      :key="crop.id" 
      :class="crop.isRedPoint ? 'red-dot' : 'green-dot'"
    >
      {{ crop.name }}
    </div>
  </div>

 

      <!-- 添加设备按钮 -->
      <el-button @click="addcrop" type="primary">添加植物</el-button>
    <div>
     
      <el-row :gutter="20">
    

 

      
          <!-- 动态生成设备卡片 -->
          <el-col :span="6" v-for="crop in crops" :key="crop.id">
            <el-card  >
              <!-- 卡片头部 -->
              <template v-slot:header>
                <div class="clearfix2">
                  <span>{{ crop.name }}{{ crop.id }} </span>
                </div>
              </template>
             
            
                

 
             
              <el-button 
              v-if="historyData" 
                @click="cgqwd(crop.id)" 
                type="info"
                >
                温度传感器数据
              </el-button>  
              <el-button 
              v-if="historyData" 
                @click="cgqsd(crop.id)" 
                type="info"
                >
                湿度传感器数据
              </el-button>  
              <el-button 
              v-if="historyData" 
                @click="cgqsz(crop.id)" 
                type="info"
                >
                水质传感器数据
              </el-button>  
  



              <el-button 
              v-if="historyData" 
                @click="cgqzh(crop.id)" 
                type="info"
                >
                综合传感器数据
              </el-button>  
  
              <!-- 删除设备按钮 -->
            <el-button @click="deletecrop(crop.id)" type="danger">
                    移除植物
              </el-button>  

            </el-card>
      
          </el-col>












      </el-row>



      

      <div v-if="visableChart">
      <div v-for="(data, type) in groupedData" :key="type">
        <h3>{{ type }}</h3>
        <div ref="chart" :id="`chart-${type}`" style="height: 400px;"></div>
      </div>
     </div>


     <el-button v-if="visableChart" @click="visableChart=false" type="danger">
                   关闭表格
            </el-button>  
     
    </div>

    <!-- 添加植物的对话框 -->
    <el-dialog title="添加植物" v-model="addcropDialogVisible" width="500px">
      <el-form :model="newDevice" label-width="100px">
        
     
        <el-form-item label="作物名称">
          <el-input v-model="cr.name" placeholder="作物名称"></el-input>
        </el-form-item>
        <el-form-item label="生长周期(天)">
          <el-input v-model="cr.growthCycle" type="number"  placeholder="作物生长周期"></el-input>
        </el-form-item>
      </el-form>

      <template v-slot:footer>
        <span class="dialog-footer">
          <el-button @click="addcropDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="addcrop2">确认添加</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
import * as echarts from 'echarts'; // 引入 ECharts 库
import api from '../api'; // 引入 API 请求文件
 
 

export default {
  data() {
    return {   
      radarStatuses: [] ,// 存储植物雷达状态
      dqvs:true,
      gxvs:false,
      yvs:false,
      jcvs:false,
      lx:0,
      groupedData: null,
       visableChart: false, // 控制图表的显示与隐藏
       historyData: [], // 存储历史数据                      
      username: '', // 存储用户名
      devices: [], // 存储设备列表
      gx:"",
      crops: [], // 存储设备列表
        addcropDialogVisible: false, 
       
    // 控制添加设备对话框的显示与隐藏
      newDevice: {
        name: '',
        type: '',
        crop:'',
        status: 'inactive',
      },
      cr:{ name:'',growthCycle:''},
    };
  },
  mounted() {
    
    const username = this.$route.query.username || ''; // 获取传递的用户名
    this.username = username; // 更新组件中的用户名
    // this.intervalId = setInterval(() => {
    //   this.dsupdate();
    // }, 3000); // 10000ms = 10秒
    // 获取设备ID列表并加载设备信息
   
    this.fetchcropsByUsername(username);
    //  // 每秒获取一次雷达数据
    
    // setInterval(this.fetchRadarData, 1000);

    
    this.intervalId = setInterval(() => {
        this.fetchRadarData(username); // 保证在定时器中 this 指向组件实例一定要加这个不然会报错
    }, 3000);
  },
  methods: {

// 根据用户名获取croppp    ID列表
  async fetchcropsByUsername(username) {
      try {
        const response = await api.get(`http://localhost:8081/api/crops/username/${username}`);
        
        this.crops = response.data.map(crop => ({
          ...crop,  }));
          // alert(this.crops)

        // 在设备加载完毕后，获取每个设备的最新数据
       
      } catch (error) {
        console.error('Failed to fetch devices:', error);
      }
    },
    
    async fetchRadarData(username) {
      try {
        const response =  await api.get(`http://localhost:8081/api/devices/crop-radar/${username}`)
        const radarData = response.data;

        // 将数据转换为前端可用格式
        this.radarStatuses = Object.entries(radarData).map(([cropId, isRedPoint]) => ({
          id: cropId,
          name: `Plant ${cropId}`, // 如果需要真实植物名称，可通过额外请求获取
          isRedPoint
        }));
      } catch (error) {
        console.error("Error fetching radar data:", error);
      }
    },







  

   async  cgqwd(cropid) {
    this.lx=1;
this.fetchSensorData(cropid,"温度")
},
 

async  cgqsd(cropid) {
  this.lx=2;
      this.fetchSensorData(cropid,"湿度") 
},

async  cgqsz(cropid) {
  this.lx=3;
   this.fetchSensorData(cropid,"水质") 
},
 

 

async  cgqzh(cropid) {
  this.exit();
  this.lx=4;
   const re= await api.get(`http://localhost:8081/api/devices/bcrop/${cropid}`);
  var deviceid=1;

  // alert(re.data+"redata");
 
  for (let de of re.data) {
      console.log("设备类型:", de.type);
      console.log("设备 ID:", de.id);

      // 判断设备类型
      if (de.type == "综合") {
        console.log("调用的是综合设备:", de);
        deviceid=de.id;
      }
    }

alert(deviceid);
  api.get(`http://localhost:8081/api/devices/${deviceid}/history/grouped`)
    .then(response => {
        // alert ("-=-=-=-=")
        this.groupedData = response.data;
        this.renderCharts(deviceid) ; })
     .catch(() => {    // 延迟1秒渲染图表 
      // 可以在这里处理错误
     });
},

//整合操作
async fetchSensorData(cropid, type) { 
  this.exit();

  try {
    const re = await api.get(`http://localhost:8081/api/devices/bcrop/${cropid}`);
    let deviceId = null;

    for (let de of re.data) {
      if (de.type === type) {
        console.log(`调用的是 ${type} 数据设备:`, de);
        deviceId = de.id;this.gx=de;
        break;
      }
    }

    if (deviceId) {
      const response = await api.get(`http://localhost:8081/api/devices/${deviceId}/history/grouped`);
      this.groupedData = response.data;//赋值全局变量
       this.gx=deviceId;
      this.renderCharts( );
    } else {
      alert(`未找到 ${type} 类型的设备`);
    }
  } catch (error) {
    console.error(`获取 ${type} 数据失败:`, error);
  }
},




  
//     handleHistoryDataClick(deviceid) {
//   api.get(`http://localhost:8081/api/devices/${deviceid}/history/grouped`)
//     .then(response => {
//         alert ("-=-=-=-=");alert("response"+response);
//         this.groupedData = response.data;
//         alert(this.groupedData+"groupeddata")
//         this.renderCharts(deviceid) ; })
//      .catch(() => {    // 延迟1秒渲染图表 
//       // 可以在这里处理错误
//      });
// },
 


   


//adsadsadsasad/asdsada
// 根据数据渲染图表












// renderCharts( ) {  
//   if(Object.keys(this.groupedData).length === 0){alert("数据为空请稍后重试") ; return;}
//   this.visableChart = true;//放这里不会出现空的 echart
//   // 在数据更新后确保 DOM 渲染完成
//   alert(JSON.stringify(this.groupedData)+"sasasa")
  
   
//   this.$nextTick(() => {                                                  
//     for (const [type, data] of Object.entries(this.groupedData)) { 
       
//       const chartElement = document.getElementById(`chart-${type}`);
      
//       // 确保 chartElement 存在
//       if (!chartElement) {alert("asaasa");
//         console.error(`无法找到 chart 元素: chart-${type}`);
//         return;
//       }
      
//       const chartInstance = echarts.init(chartElement);
       
//       const option = {
//         title: {
//           text: `${type} 数据`,
//           left: 'center',
//         },
//         tooltip: {
//           trigger: 'axis',
//         },
//         xAxis: {
//           type: 'category',
//           data: data.map(item => item.timestamp),
//           axisLabel: {
//             rotate: 45,  // 旋转标签以便显示
//           },
//         },
//         yAxis: {
//           type: 'value',
//         },
//             series: [{
//         data: data.map(item => item.value),
//         type: 'line', // 使用折线图类型
//         lineStyle: {width:1,type: 'solid',  color: '#ff6347',  // 设置折线的颜色，可以是任何合法的颜色值
//               },
//         areaStyle: {
//         color: 'rgba(255, 99, 71, 1)',  // 设置填充颜色，确保与折线颜色一致，调整透明度
//           },
// }],
 
// };

//       chartInstance.setOption(option);   
//     } 
//   })         


// },  


renderCharts() {///可用版本

  let chartType;
switch (this.lx) {
    case 1:
        chartType = 'bar';
        break;
    case 2:
    chartType = 'line';
        break;
    case 3:
        chartType = 'scatter';
        break;
    default:
        chartType = 'line';  // 默认类型为折线图
}
  if (Object.keys(this.groupedData).length === 0) {
    alert("数据为空请稍后重试");
    return;
  }

  this.visableChart = true; // 确保图表可见

  // 在数据更新后确保 DOM 渲染完成
  this.$nextTick(() => {
    for (const [type, data] of Object.entries(this.groupedData)) {
      const chartElement = document.getElementById(`chart-${type}`);
      
      // 确保 chartElement 存在
      if (!chartElement) {
        console.error(`无法找到 chart 元素: chart-${type}`);
        return;
      }
      
      // 初始化图表实例
      const chartInstance = echarts.init(chartElement);
      
      // 定义图表的初始配置项
      const option = {
        title: {
          text: `${type} 数据`,
          left: 'center',
        },
        tooltip: {
          trigger: 'axis',
        },
        xAxis: {
          type: 'category',
          data: data.map(item => {
  const [year, month, day, hour, minute, second, millisecond] = item.timestamp;
  const date = new Date(year, month - 1, day, hour, minute, second, millisecond / 1e6); // 注意：月份需要减 1
  return date.toLocaleString('en-US', {
    month: 'short', // 显示月份
    day: 'numeric', // 显示日期
    hour: '2-digit', // 显示小时
    minute: '2-digit', // 显示分钟
  });
}),
          axisLabel: {
            rotate: 10,  // 旋转标签以便显示
          },
        },
        yAxis: {
          type: 'value',
        },
        series: [{
          data: data.map(item => item.value),
          type: chartType, // 使用折线图类型
          lineStyle: {
            width: 1,
            type: 'solid',
            color: '#ff6347', // 设置折线的颜色
          },
          areaStyle: {
            color: 'rgba(255, 99, 71, 1)', // 设置填充颜色
          },
        }],
      };

      // 设置图表的配置项
      chartInstance.setOption(option);

      // 动态获取数据并更新图表
      setInterval(() => {
        this.fetchNewData(type, chartInstance);
      }, 5000); // 每5秒钟更新一次数据
    }
  });
},

// 获取新数据并更新图表
fetchNewData(type, chartInstance) {
  // 通过 API 获取新的数据（替换为您的实际 API 请求）
 
//  是5s重新后获取的数据集合 比grouped新5s
  fetch(`http://localhost:8081/api/devices/${this.gx}/history/grouped`)
    .then(response => response.json())
    .then(newData => {////this.groupedData=newData  现在就成旧的了可以在这里更新接着用
          //后台不更新就会一直插入旧值  
      console.log(newData+"sasanew"+"this.growped-->"+this.groupedData)
      console.log(this.groupedData+"groupeddddnew")
    //  alert(JSON.stringify(newData)+"asdasdanewdata");
      // 假设 newData 是新的数据，更新图表
      const option = chartInstance.getOption(); // 获取当前的图表配置项
 
      for (const [type, data] of Object.entries(newData)) {
     // 如果数据过多，可以移除最旧的数据
     if (option.xAxis[0].data.length > 22) {
        option.xAxis[0].data.shift();
        option.series[0].data.shift();
      }


           
      // 获取当前数据中的最后一条
      const lastItem = data[data.length - 1];
     
     
       
      option.xAxis[0].data.push(lastItem.timestamp); // 将新时间戳推入
      option.series[0].data.push(lastItem.value); // 将新值推入
       console.log(type+"<---->这次遍历的传感器类型");

      }
      




























     
      // 更新 xAxis 和 series 数据
      
      

      // 更新图表显示
      chartInstance.setOption(option);
    })
    .catch(error => console.error('Error fetching data:', error));
}
,




















async addcrop(){  this.addcropDialogVisible = true;},
async addcrop2() { 
 
if(!(this.cr.name && this.cr.growthCycle)) { 
alert("不能有空的") ;return;
}



const crop={ 
name:this.cr.name,
growthCycle:this.cr.growthCycle+"天",
}

try {const responsec = await api.post('http://localhost:8081/api/crops',crop);

   this.newDevice.crop={name:this.cr.name,growthCycle:this.cr.growthCycle+"天"}   ;
 crop.id=responsec.data.id;
// alert(responsec.data.id+"返回的id是----");
// alert(crop.id+"-0-0--封装的cropid");
// alert("植物添加成功");

 
const dev1={name:crop.id+crop.name+"湿度检测器",
            type:"湿度",
            status:"active",
             crop:responsec.data
 
};const dev2={name:crop.id+crop.name+"温度检测器",
            type:"温度",
            status:"active",
             crop:responsec.data
 
};const dev3={name:crop.id+crop.name+"水质检测器",
            type:"水质",
            status:"active",
             crop:responsec.data
 
};const dev4={name:crop.id+crop.name+"综合检测器",
            type:"综合",
            status:"active",
             crop:responsec.data
 
};
 
const responsed1 = await api.post('http://localhost:8081/api/devices', dev1);
 
const responsed2 = await api.post('http://localhost:8081/api/devices', dev2);
 
const responsed3 = await api.post('http://localhost:8081/api/devices', dev3);

const responsed4 = await api.post('http://localhost:8081/api/devices', dev4);
if((responsed1 && responsed2 && responsed3 && responsed4)){
          alert(crop.id+crop.name+"植物绑定传感器成功")    } 
      
        const  uc1={
             password:this.password,
             username:this.username,
             crop:crop
          };
          
 const responsed5 = await api.post('http://localhost:8081/api/crops/uc', uc1);
          
        console.log(responsed5+"插入到了usercrops")
        
          const cc=this.fetchcropsByUsername(this.username);
          this.crops=cc;
          this.addcropDialogVisible=false;
        
        
        
        } catch (error) {
console.error('Failed to add device:', error);alert('添加植物，请重试');
}
},
  
  // dele植物
  async deletecrop(cropid) {
    
    const confirmDelete = window.confirm("确定要删除这棵植物吗？");
    if(!confirmDelete){ return;}
    
    try { 

     
       
    
       
       //this.fetchLatestData();  // 重新加载设备列表
      // 3. 删除设备后，重新植物信息
      await api.delete(`http://localhost:8081/api/crops/${cropid}`); 
        
      alert("植物删除成功");
      this.fetchcropsByUsername(this.username);  // 提示用户删除成功
       this.crops =this.fetchcropsByUsername(this.username); 
       //会自动渲染
        // 提示用户删除成功
        alert('设备删除成功'); // 重新加载设备列表
      


      
    } catch (error) {
      console.error('删除植物失败:', error);
      alert('删除植物-----------失败，请重试');
    }
  },



  formatTimestamp(timestamp) {
      const date = new Date(timestamp);
      return date.toLocaleString(); // 格式化时间戳为本地时间
    },




exit(){ 
  this.visableChart=false;  },



 
  }
};
</script>
<style>
.red-dot {
  color: rgba(181, 28, 28, 0.903);
}
.green-dot {
  color: rgb(51, 226, 51);
}
</style>