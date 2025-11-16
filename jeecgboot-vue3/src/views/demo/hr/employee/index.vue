<template>
  <div class="employee-page">
    <!-- 查询区域 -->
    <BasicTable @register="registerTable" :searchInfo="searchForm">
      <!-- 操作列 -->
      <template #action="{ record }">
        <TableAction
          :actions="[
            {
              icon: 'clarity:note-edit-line',
              tooltip: '编辑',
              onClick: handleEdit.bind(null, record),
            },
            {
              icon: 'ant-design:delete-outlined',
              color: 'error',
              tooltip: '删除',
              popConfirm: {
                title: '是否确认删除',
                confirm: handleDelete.bind(null, record.id),
              },
            },
          ]"
        />
      </template>
      <template #toolbar>
        <a-button type="primary" @click="handleAdd"> 新增员工 </a-button>
        <a-button type="primary" @click="handleExport"> 导出 </a-button>
        <a-upload
          name="file"
          :showUploadList="false"
          :multiple="false"
          :headers="tokenHeader"
          :action="importExcelUrl"
          @change="handleImportExcel"
        >
          <a-button type="primary"> 导入 </a-button>
        </a-upload>
      </template>
    </BasicTable>

    <!-- 新增/编辑弹窗 -->
    <EmployeeModal @register="registerModal" @success="handleSuccess" />
  </div>
</template>

<script lang="ts" setup>
import { ref, computed, unref } from 'vue';
import type { Recordable } from '/@/types';
import { BasicTable, useTable, TableAction } from '/@/components/Table';
import { useModal } from '/@/components/Modal';
import { useMessage } from '/@/hooks/web/useMessage';
import { useGlobSetting } from '/@/hooks/setting';
import { downloadFile } from '/@/utils/common/renderUtils';
import {
  deleteEmployee,
  exportEmployeeData,
  getEmployeeList,
} from './api/employee';
import { columns, searchFormSchema } from './employee.data';
import EmployeeModal from './components/EmployeeModal.vue';

const { createMessage } = useMessage();
const [registerModal, { openModal }] = useModal();
const globSetting = useGlobSetting();

// 表格配置
const [registerTable, { reload, getForm }] = useTable({
  title: '员工管理',
  api: getEmployeeList,
  columns,
  formConfig: {
    labelWidth: 120,
    schemas: searchFormSchema,
    autoSubmitOnEnter: true,
  },
  useSearchForm: true,
  showTableSetting: true,
  bordered: true,
  showIndexColumn: true,
  actionColumn: {
    width: 120,
    title: '操作',
    dataIndex: 'action',
    slots: { customRender: 'action' },
  },
});

// 搜索表单数据
const searchForm = ref({});

// 导入Excel的URL
const importExcelUrl = computed(() => `${globSetting.apiUrl}/demo/hr/employee/importExcel`);

// Token header
const tokenHeader = computed(() => ({
  'X-Access-Token': localStorage.getItem('ACCESS_TOKEN') || '',
}));

// 状态颜色
const getStatusColor = (status: string) => {
  const statusColors: Record<string, string> = {
    active: 'green',
    inactive: 'red',
    trial: 'orange',
    retired: 'gray',
  };
  return statusColors[status] || 'default';
};

// 新增方法
const handleAdd = () => {
  openModal(true, {
    isUpdate: false,
  });
};

// 编辑方法
const handleEdit = (record: Recordable) => {
  openModal(true, {
    record,
    isUpdate: true,
  });
};

// 删除方法
const handleDelete = async (id: string) => {
  try {
    await deleteEmployee(id);
    createMessage.success('删除成功');
    reload();
  } catch (error) {
    createMessage.error('删除失败');
  }
};

// 导出方法
const handleExport = async () => {
  try {
    const params = getForm().getFieldsValue();
    const data = await exportEmployeeData(params);
    downloadFile(data, `员工信息_${new Date().getTime()}.xlsx`);
  } catch (error) {
    createMessage.error('导出失败');
  }
};

// 导入Excel方法
const handleImportExcel = async (info: any) => {
  if (info.file.status === 'done') {
    if (info.file.response.success) {
      createMessage.success('导入成功');
      reload();
    } else {
      createMessage.error(info.file.response.message || '导入失败');
    }
  } else if (info.file.status === 'error') {
    createMessage.error('文件上传失败');
  }
};

// 弹窗成功回调
const handleSuccess = () => {
  reload();
};
</script>

<style lang="less" scoped>
.employee-page {
  padding: 12px;
}
</style>