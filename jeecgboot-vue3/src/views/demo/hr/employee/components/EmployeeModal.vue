<template>
  <BasicModal
    v-bind="$attrs"
    :title="modalTitle"
    :width="800"
    @register="registerModal"
    @ok="handleSubmit"
  >
    <BasicForm @register="registerForm" />
  </BasicModal>
</template>

<script lang="ts" setup>
import { ref, computed, unref } from 'vue';
import { BasicModal, useModalInner } from '/@/components/Modal';
import { BasicForm, useForm } from '/@/components/Form';
import { useMessage } from '/@/hooks/web/useMessage';
import {
  addEmployee,
  editEmployee,
  getEmployeeById,
} from '../api/employee';
import { formSchema } from '../employee.data';

const emit = defineEmits(['success', 'register']);
const { createMessage } = useMessage();

const isUpdate = ref(true);
const rowId = ref('');

const modalTitle = computed(() => (!unref(isUpdate) ? '新增员工' : '编辑员工'));

const [registerForm, { setFieldsValue, resetFields, validate }] = useForm({
  labelWidth: 100,
  schemas: formSchema,
  showActionButtonGroup: false,
  baseColProps: { lg: 24, md: 24 },
});

const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
  // 重置表单
  await resetFields();
  setModalProps({ confirmLoading: false });
  isUpdate.value = !!data?.isUpdate;

  if (unref(isUpdate) && data.record) {
    // 编辑模式，使用传入的record数据填充表单
    rowId.value = data.record.id;
    const recordData = data.record;

    console.log('编辑模式 - 原始数据:', recordData);

    // 构建表单数据，处理字段名映射
    const formData = {
      employeeNo: recordData.employeeNo || '',
      name: recordData.name || '',
      gender: recordData.gender,
      birthDate: recordData.birthDate,
      idCard: recordData.idCard || '',
      mobile: recordData.mobile || '',
      email: recordData.email || '',
      position: recordData.position || '',
      hireDate: recordData.hireDate,
      employeeStatus: recordData.employeeStatus || 'active',
      departmentId: recordData.departmentId || '',
      education: recordData.education,
      address: recordData.address || '',
      emergencyContact: recordData.emergencyContact || '',
      emergencyPhone: recordData.emergencyPhone || '',
      remark: recordData.remark || '',
    };

    console.log('编辑模式 - 表单数据:', formData);

    try {
      await setFieldsValue(formData);
      console.log('表单数据设置成功');
    } catch (error) {
      console.error('设置表单数据失败:', error);
      createMessage.error('设置表单数据失败');
    }
  } else {
    // 新增模式，设置默认值
    console.log('新增模式');
    try {
      await setFieldsValue({
        employeeStatus: 'active',
      });
      console.log('新增模式默认值设置成功');
    } catch (error) {
      console.error('设置默认值失败:', error);
    }
  }
});

// 提交表单
const handleSubmit = async () => {
  // 重置loading状态
  setModalProps({ confirmLoading: false });

  try {
    // 1. 首先验证表单
    console.log('开始验证表单...');
    const values = await validate();
    console.log('表单验证通过:', values);
    setModalProps({ confirmLoading: true });

    try {
      let res;
      if (unref(isUpdate)) {
        // 编辑
        console.log('开始编辑员工...');
        res = await editEmployee({ ...values, id: rowId.value });
      } else {
        // 新增
        console.log('开始新增员工...', values);
        res = await addEmployee(values);
      }

      console.log('API响应:', res);

      // 2. 检查API响应
      if (res && res.success !== false) {
        createMessage.success(unref(isUpdate) ? '编辑成功' : '新增成功');
        closeModal();
        emit('success');
      } else {
        // 如果API返回success: false，显示错误消息
        createMessage.error(res?.message || (unref(isUpdate) ? '编辑失败' : '新增失败'));
      }
    } catch (apiError) {
      // API调用异常
      console.error('API调用异常:', apiError);
      // 直接抛出，让外层catch处理
      throw apiError;
    }
  } catch (error) {
    // 捕获所有异常
    console.error('操作异常详情:', error);

    // 检查是否是表单验证错误
    if (error.message?.includes('required') || error.message?.includes('请输入')) {
      // 表单验证错误，不显示额外消息
      return;
    }

    // 其他错误
    createMessage.error('操作失败，请检查输入并重试');
  } finally {
    setModalProps({ confirmLoading: false });
  }
};
</script>